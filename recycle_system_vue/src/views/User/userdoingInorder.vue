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
      <el-table-column prop="totalAmount" label="总价" width="100">
      </el-table-column>
      <el-table-column label="配送员" width="140">
        <template slot-scope="scope">
          <span v-if="scope.row.collectorName!=null">{{scope.row.collectorName}}</span>
          <span v-else>未分配</span>
        </template>
      </el-table-column>
      <el-table-column  label="联系电话" width="200">
        <template slot-scope="scope">
          <span v-if="scope.row.phone!=null">{{scope.row.phone}}</span>
          <span v-else>未分配</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="400">
        <template slot-scope="scope">
          <el-popover placement="right" width="400" trigger="click">
            <el-table :data="recycleOrdersDetailVoList">
              <el-table-column width="100" prop="itemName" label="物品"></el-table-column>
              <el-table-column width="100" prop="quantity" label="数量(斤)"></el-table-column>
              <el-table-column width="100" prop="itemPrice" label="单价(元/斤)"></el-table-column>
              <el-table-column width="100" prop="sum" label="总价(元)"></el-table-column>
            </el-table>
            <el-button  type="primary" round slot="reference" @click="getDetail(scope.row.recycleOrderId)" >查询订单详情</el-button>
          </el-popover>
          <el-button  type="primary" round @click="jumpUpdate(scope.row.recycleOrderId)">修改订单</el-button>
          <el-button  type="primary" round @click="Delete(scope.row.recycleOrderId)">删除订单</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background layout="total, prev, pager, next, jumper" :page-size="pageSize" :total="total" @current-change="page"></el-pagination>
  </div>
</template>
<script>
  export default {
    methods:{
      Delete(recycleOrderId){
        const _this=this;
        axios.get('http://localhost:8181/userdeleteorder/'+recycleOrderId+'').then(function (resp) {
          console.log(resp)
          alert('删除成功')
        })
      },
      jumpUpdate(recycleOrderId){
        this.$router.push({
          path: "/userupdateorder",
          query: {recycleOrderId: recycleOrderId }
        });
      },
      findbycellectorname(){
        const _this=this
          axios.post('http://localhost:8181/userfindAllDoingOrders/1/8',this.userrequire).then(function (resp) {
            _this.tableData=resp.data.list
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
        axios.post('http://localhost:8181/userfindAllDoingOrders/1/8',this.userrequire).then(function (resp) {
          console.log(resp)
          _this.tableData=resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      getDetail(recycleOrderId){
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
          axios.post('http://localhost:8181/userfindAllDoingOrders/'+currentPage+'/8',this.userrequire).then(function (resp) {
            _this.tableData = resp.data.list
            _this.pageSize = resp.data.pageSize
            _this.total = resp.data.total
          })
      }
    },
    watch:{
      $route(){
        window.location.reload()
      }
    },
    created () {
      const _this=this;
      axios.post('http://localhost:8181/userfindAllDoingOrders/1/8',this.userrequire).then(function (resp) {
        console.log(resp)
        _this.show=true
        _this.tableData=resp.data.list
        _this.pageSize = resp.data.pageSize
        _this.total = resp.data.total
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
        tableData: [{
          recycleOrderId: 1,
          scheduledTime: '12月15日 下午17：00',
          collectorName: '陈南',
          totalAmount:'',
          phone:13615787610,
        }],
      }
    }
  }
</script>

<style scoped>

</style>
