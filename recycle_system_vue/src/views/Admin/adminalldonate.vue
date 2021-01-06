<template>
  <div v-if="show">
    根据用户名查询订单：<el-input v-model="adminrequire.username" placeholder="请输入快递员名" style="width: 130px"></el-input>
    根据快递员姓名查询订单:<el-input v-model="adminrequire.collectorname" placeholder="请输入用户名" style="width: 120px"></el-input>
    根据日期查询订单：
    <el-select v-model="adminrequire.datepick" placeholder="请选择" style="width: 110px">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-date-picker v-if="this.adminrequire.datepick=='day'" v-model="adminrequire.datebyday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd" style="width: 190px;"></el-date-picker>
    <el-date-picker v-if="this.adminrequire.datepick=='month'" v-model="adminrequire.datebymonth" type="month" placeholder="选择月" format="yyyy年MM月" value-format="yyyy-MM" style="width: 150px;"></el-date-picker>
    <el-button  type="primary" round  @click="findbyrequire" style="width: 80px">查询</el-button>
    <el-button  type="primary" round  @click="findall">查询全部</el-button>

    <el-table :data="tableData">
      <el-table-column prop="donateId" label="捐赠号" width="140">
      </el-table-column>
      <el-table-column prop="scheduledTime" label="预约时间" width="200">
      </el-table-column>
      <el-table-column  label=" 完成时间" width="200">
        <template slot-scope="scope">
          <span v-if="scope.row.finishedTime!=null">{{scope.row.finishedTime}}</span>
          <span v-else>未完成</span>
        </template>
      </el-table-column>
      <el-table-column  label="评价" width="200">
        <template slot-scope="scope">
          <el-popover placement="right" width="400" trigger="click">
            <el-table :data="evaluationForm">
              <el-table-column width="100" prop="evaluationScore" label="分数"></el-table-column>
              <el-table-column width="300" prop="evaluationDetails" label="评价"></el-table-column>
            </el-table>
            <el-button  v-if="scope.row.evaluationId!=null&&scope.row.finishedTime!=null" type="primary" round slot="reference" @click="selectEvaluation(scope.row.evaluationId)">查看评价</el-button>
          </el-popover>
          <span v-if="scope.row.evaluationId==null&&scope.row.finishedTime!=null">未评价</span>
          <span v-if="scope.row.finishedTime==null">未完成</span>
        </template>
      </el-table-column>
      <el-table-column prop="userName" label="用户" width="140">
      </el-table-column>
      <el-table-column label="派送员" width="200px">
        <template slot-scope="scope" >
          <span v-if="scope.row.collectorName!=null">{{scope.row.collectorName}}</span>
          <span v-else><el-button  type="primary" round @click="edit(scope.row)">选择派送员</el-button></span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-popover placement="right" width="400" trigger="click">
            <div>{{scope.row.donateDetail}}</div>
            <el-button  type="primary" round slot="reference" >查询捐赠详情</el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background layout="total, prev, pager, next, jumper" :page-size="pageSize" :total="total" @current-change="page"></el-pagination>
    <el-dialog :visible.sync="editVisible">
      <el-form  :model="editForm" ref="editForm" @submit.prevent.native >
        <el-select v-model="editForm.collectorName" placeholder="请选择" style="width: 500px">
          <el-option v-for="item in collector" :key="item.collectorId" :label="item.collectorName" :value="item.collectorName"></el-option>
        </el-select>
        <div>
          <el-button @click="closeDialog()">取消</el-button>
          <el-button type="primary" @click="submit()">确定</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    methods:{
      selectEvaluation(evaluationId){
        const _this=this
        axios.get('http://localhost:8181/Evaluation/'+evaluationId+'').then(function (resp) {
          console.log(resp)
          _this.evaluationForm.pop()
          _this.evaluationForm.push(resp.data)
        })
      },
      edit(row){
        this.editForm.donateId=row.donateId
        this.editForm.collectorName=''
        this.editVisible=true
      },
      closeDialog(){
        this.editVisible=false
      },
      submit(){
        const _this=this
        axios.get('http://localhost:8181/donateplacecollector/'+this.editForm.donateId+'/'+this.editForm.collectorName+'').then(function (resp) {
          console.log(resp)
          for(var j=0,len=_this.tableData.length;j<len;j++){
            if(_this.tableData[j].donateId==resp.data){
              _this.tableData[j].collectorName=_this.editForm.collectorName
            }
          }
          if(resp.data!=0){
            _this.$alert('修改派送员成功','消息',{
              confirmButtonText:'确定',
            });
          }
          _this.editVisible=false
        })
      },
      findbyrequire(){
        const _this = this
        axios.post('http://localhost:8181/adminfindalldonate/1/4',this.adminrequire).then(function (resp) {
          _this.tableData=resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      findall(){
        const _this=this
        this.adminrequire.username=''
        this.adminrequire.collectorname=''
        this.adminrequire.datebymonth=''
        this.adminrequire.datebyday=''
        this.adminrequire.datepick='day'
        axios.post('http://localhost:8181/adminfindalldonate/1/4',this.adminrequire).then(function (resp) {
          _this.tableData=resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      page(currentPage){
        const _this = this
        axios.post('http://localhost:8181/adminfindalldonate/'+currentPage+'/4',this.adminrequire).then(function (resp) {
          _this.tableData=resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
    },
    created () {
      const _this=this;
      axios.post('http://localhost:8181/adminfindalldonate/1/4',this.adminrequire).then(function (resp) {
        console.log(resp)
        _this.tableData=resp.data.list
        _this.pageSize = resp.data.pageSize
        _this.total = resp.data.total
        _this.show=true
      })
      axios.get('http://localhost:8181/allCollector').then(function (r) {
        console.log(r)
        _this.collector=r.data
      })
    },
    data(){
      return{
        show:false,
        editVisible:false,
        editForm:{
          donateId:1,
          collectorName:'',
        },
        pageSize:1,
        total:1,
        adminrequire:{
          username:'',
          collectorname:'',
          datebyday:'',
          datebymonth:'',
          datepick:'day',
        },
        collector:[
          {
            collectorId:1,
            collectorName:'杨昕语',
          },
          {
            collectorId:2,
            collectorName:'陈南',
          },
          {
            collectorId:3,
            collectorName:'陈小南',
          },
        ],
        options:[
          {
            value:'day',
            label:'按日查询',
          },
          {
            value:'month',
            label:'按月查询',
          }
        ],
        evaluationForm:[{
          evaluationId:1,
          evaluationDetails:'asfs',
          evaluationScore:100,
        }],
        tableData: [{
          donateId: 1,
          scheduledTime: '12月15日 下午17：00',
          finishedTime: '12月15日 下午17：10',
          userName: '陈南',
          evaluationId:'',
          collectorName: '陈南',
        },
          {
            donateId: 2,
            scheduledTime: '12月15日 下午17：00',
            finishedTime:'12月15日 下午17：10',
            userName: '陈南',
            evaluationId:'',
            collectorName: '陈南',
        }]
      }
    }
  }
</script>

<style scoped>

</style>
