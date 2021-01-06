<template>
  <div v-if="show">
    根据快递员姓名查询订单:<el-input v-model="userrequire.collectorname" placeholder="请输入快递员姓名" style="width: 200px"></el-input>
    根据日期查询订单：
    <el-select v-model="userrequire.datepick" placeholder="请选择">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-date-picker v-if="this.userrequire.datepick=='day'" v-model="userrequire.datebyday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd"></el-date-picker>
    <el-date-picker v-if="this.userrequire.datepick=='month'" v-model="userrequire.datebymonth" type="month" placeholder="选择月" format="yyyy年MM月" value-format="yyyy-MM"></el-date-picker>
    <el-button  type="primary" round  @click="findbycellectorname">查询</el-button>
    <el-button  type="primary" round  @click="findall">查询全部</el-button>

    <el-table :data="tableData">
      <el-table-column prop="recycleOrderId" label="订单号" width="80">
      </el-table-column>
      <el-table-column prop="scheduledTime" label="预约时间" width="200">
      </el-table-column>
      <el-table-column  label="完成时间" width="200">
        <template slot-scope="scope">
          <span v-if="scope.row.finishedTime!=null">{{scope.row.finishedTime}}</span>
          <span v-else>未完成</span>
        </template>
      </el-table-column>
      <el-table-column prop="totalAmount" label="总价" width="100">
      </el-table-column>
      <el-table-column  label="评价" width="150">
        <template slot-scope="scope">
          <el-popover placement="right" width="400" trigger="click">
            <el-table :data="evaluationForm">
              <el-table-column width="100" prop="evaluationScore" label="分数"></el-table-column>
              <el-table-column width="300" prop="evaluationDetails" label="评价"></el-table-column>
            </el-table>
            <el-button  v-if="scope.row.evaluationId!=null&&scope.row.finishedTime!=null" type="primary" round slot="reference" @click="selectEvaluation(scope.row.evaluationId)">查看评价</el-button>
          </el-popover>
          <span v-if="scope.row.evaluationId==null&&scope.row.finishedTime!=null"><el-button type="primary" round @click="jump(scope.row.recycleOrderId)">评价</el-button></span>
          <span v-if="scope.row.finishedTime==null">未完成</span>
        </template>
      </el-table-column>
      <el-table-column label="配送员" width="140">
        <template slot-scope="scope">
          <span v-if="scope.row.collectorName!=null">{{scope.row.collectorName}}</span>
          <span v-else>未分配</span>
        </template>
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
            <el-table :data="recycleOrdersDetailVoList">
              <el-table-column width="100" prop="itemName" label="物品"></el-table-column>
              <el-table-column width="100" prop="quantity" label="数量(斤)"></el-table-column>
              <el-table-column width="100" prop="itemPrice" label="单价(元/斤)"></el-table-column>
              <el-table-column width="100" prop="sum" label="总价(元)"></el-table-column>
            </el-table>
            <el-button  type="primary" round slot="reference" @click="showDetail(scope.row.recycleOrderId)">查询订单详情</el-button>
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
      selectEvaluation(evaluationId){
        const _this=this
        axios.get('http://localhost:8181/Evaluation/'+evaluationId+'').then(function (resp) {
          console.log(resp)
          _this.evaluationForm.pop()
          _this.evaluationForm.push(resp.data)
        })
      },
      jump(recycleOrderId){
        this.$router.push({
          path: "/evaluate",
          query: {recycleOrderId: recycleOrderId }
        });
      },
      findbycellectorname () {
        const _this = this
        this.selectbyrequire=true
        this.selectbynormal=false
        axios.post('http://localhost:8181/userfindAllOrders/1/8',this.userrequire).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      findall(){
        const _this=this
        this.userrequire.collectorname=''
        this.userrequire.datebymonth=''
        this.userrequire.datebyday=''
        this.userrequire.datepick='day'
        axios.post('http://localhost:8181/userfindAllOrders/1/8',this.userrequire).then(function (resp) {
          console.log(resp
          )
          _this.tableData=resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      showDetail(recycleOrderId){
        const _this = this
        axios.get('http://localhost:8181/OrdersDetail/'+recycleOrderId+'').then(function (resp) {
          _this.recycleOrdersDetailVoList=resp.data
          for(var j=0,len=_this.recycleOrdersDetailVoList.length;j<len;j++){
            _this.recycleOrdersDetailVoList[j].sum = _this.recycleOrdersDetailVoList[j].quantity*_this.recycleOrdersDetailVoList[j].itemPrice
          }
        })
      },
      page(currentPage){
        const _this = this
          axios.post('http://localhost:8181/userfindAllOrders/'+currentPage+'/8',this.userrequire).then(function (resp) {
            _this.tableData = resp.data.list
            _this.pageSize = resp.data.pageSize
            _this.total = resp.data.total
          })
      }
    },
    created () {
      const _this=this;
      axios.post('http://localhost:8181/userfindAllOrders/1/8',this.userrequire).then(function (resp) {
        console.log(resp)
        _this.show=true
        _this.pageSize = resp.data.pageSize
        _this.total = resp.data.total
        _this.tableData=resp.data.list

      })
    },
    data(){
      return{
        show:false,
        pageSize:1,
        total:1,
        userrequire:{
          id:this.$store.getters.getUserId,
          collectorname:'',
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
        recycleOrdersDetailVoList:[{
          itemName: '纸板',
          quantity: 10,
          itemPrice: 1,
          sum:'',
        }, {
          itemName: '易拉罐',
          quantity: 20,
          itemPrice: 0.1,
          sum:'',
        }, {
          itemName: '啤酒瓶',
          quantity: 5,
          itemPrice: 1,
          sum:'',
        }, {
          itemName: '旧衣服',
          quantity: 20,
          itemPrice: 0.5,
          sum:'',
        }],
        evaluationForm:[{
          evaluationId:1,
          evaluationDetails:'asfs',
          evaluationScore:100,
        }],
        tableData: [{
          recycleOrderId: 1,
          scheduledTime: '12月15日 下午17：00',
          finishedTime: '12月15日 下午17：10',
          collectorName: '陈南',
          evaluationId:'',
          totalAmount:'',
          phone: 13615787610,
        },
          {
            recycleOrderId: 2,
            scheduledTime: '12月15日 下午17：00',
            finishedTime:'12月15日 下午17：10',
            collectorName: '陈南',
            evaluationId:'',
            totalAmount:'',
            phone:13615787610,
          }]
      }
    }
  }
</script>

<style scoped>

</style>
