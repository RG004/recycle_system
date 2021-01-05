<template>
  <div v-if="show">
    根据用户用户名查询订单:<el-input v-model="collectorrequire.username" placeholder="请输入用户姓名" style="width: 200px"></el-input>
    根据日期查询订单：
    <el-select v-model="collectorrequire.datepick" placeholder="请选择">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-date-picker v-if="this.collectorrequire.datepick=='day'" v-model="collectorrequire.datebyday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd"></el-date-picker>
    <el-date-picker v-if="this.collectorrequire.datepick=='month'" v-model="collectorrequire.datebymonth" type="month" placeholder="选择月" format="yyyy年MM月" value-format="yyyy-MM"></el-date-picker>
    <el-button  type="primary" round  @click="findbyrequire">查询</el-button>
    <el-button  type="primary" round  @click="findall">查询全部</el-button>

    <el-table :data="tableData">
      <el-table-column prop="donateId" label="订单号" width="140">
      </el-table-column>
      <el-table-column prop="scheduledTime" label="预约时间" width="300">
      </el-table-column>
      <el-table-column  label="完成时间" width="200">
        <template slot-scope="scope">
          <span v-if="scope.row.finishedTime!=null">{{scope.row.finishedTime}}</span>
          <span v-else>未完成</span>
        </template>
      </el-table-column>
      <el-table-column  label="评价" width="200">
        <template slot-scope="scope">
          <span v-if="scope.row.evaluationId!=null&&scope.row.finishedTime!=null"><el-button type="primary" round >查看评价</el-button></span>
          <span v-else-if="scope.row.evaluationId==null&&scope.row.finishedTime!=null">未评价</span>
          <span v-else>未完成</span>
        </template>
      </el-table-column>
      <el-table-column prop="userName" label="用户" width="140">
      </el-table-column>
      <el-table-column fixed=right  label="联系电话" >
        <template slot-scope="scope">
          <span v-if="scope.row.phone!=null">{{scope.row.phone}}</span>
          <span v-else>未分配</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-popover placement="right" width="400" trigger="click">
            <div>{{scope.row.donateDetail}}</div>
            <el-button  type="primary" round slot="reference" >捐赠详情</el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background layout="total, prev, pager, next, jumper" :page-size="pageSize" :total="total" @current-change="page"></el-pagination>
  </div>
</template>

<script>
  export default {
    methods:{
      findbyrequire() {
        const _this = this
        axios.post('http://localhost:8181/collectorfindalldonate/1/8',this.collectorrequire).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      findall(){
        const _this=this
        this.collectorrequire.username=''
        this.collectorrequire.datebymonth=''
        this.collectorrequire.datebyday=''
        this.collectorrequire.datepick='day'
        axios.post('http://localhost:8181/collectorfindalldonate/1/8',this.collectorrequire).then(function (resp) {
          _this.tableData=resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      page(currentPage) {
        const _this = this
        axios.post('http://localhost:8181/collectorfindalldonate/' + currentPage + '/8', _this.collectorrequire).then(function (resp) {
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      }
    },
    created () {
      const _this=this;
      axios.post('http://localhost:8181/collectorfindalldonate/1/8',this.collectorrequire).then(function (resp) {
        console.log(resp)
        _this.tableData=resp.data.list
        _this.pageSize = resp.data.pageSize
        _this.total = resp.data.total
        _this.show=true
      })
    },
    data(){
      return{
        show:false,
        pageSize:1,
        total:1,
        collectorrequire:{
          id:this.$store.getters.getCollectorId,
          username:'',
          datebyday:'',
          datebymonth:'',
          datepick:'day',//判断是按月查询还是按日查询
        },
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
        tableData: [{
          donateId: 1,
          scheduledTime: '12月15日 下午17：00',
          finishedTime: '12月15日 下午17：10',
          userName: '陈南',
          evaluationId:'',
          phone: 13615787610,
          donateDetail:'',
        },
          {
            donateId: 2,
            scheduledTime: '12月15日 下午17：00',
            finishedTime:null,
            userName: '陈南',
            evaluationId:null,
            phone:13615787610,
            donateDetail:'',
          }]
      }
    }
  }
</script>

<style scoped>

</style>
