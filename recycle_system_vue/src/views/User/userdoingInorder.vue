<template>
  <div>
    根据快递员姓名查询订单:<el-input v-model="cellectorname" placeholder="请输入快递员姓名" style="width: 200px"></el-input>
    <el-button  type="primary" round slot="reference" @click="findbycellectorname(cellectorname)">查询</el-button>

    <el-table :data="tableData">

      <el-table-column prop="recycleOrderId" label="订单号" width="140">
      </el-table-column>
      <el-table-column prop="scheduledTime" label="预约时间" width="300">
      </el-table-column>
      <el-table-column prop="collectorName" label="配送员" width="140">
      </el-table-column>
      <el-table-column prop="phone" label="联系电话" >
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
            <el-button  type="primary" round slot="reference" @click="getDetail(scope.row.recycleOrderId)" >查询订单详情</el-button>
          </el-popover>
          <el-button  type="primary" round @click="jumpUpdate(scope.row.recycleOrderId)" >修改订单</el-button>
        </template>

      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next"
      :page-size="pageSize"
      :total="total"
      @current-change="page">
    </el-pagination>

  </div>

</template>

<script>
  export default {
    methods:{
      findbycellectorname(collectorname){
        alert(this.$store.getters.getUserId)
        axios.get('http://localhost:8181/userFindordersBycellectorname/'+_this.$store.getters.getUserId+'/'+cellectorname+'/1/2').then(function (resp) {

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
        axios.get('http://localhost:8181/userDoingorders/'+_this.$store.getters.getUserId+'/'+currentPage+'/1').then(function(resp){
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      jumpUpdate(recycleOrderId){
        this.$router.push({
          path: "/userupdateorder",
          query: {recycleOrderId: recycleOrderId }
        });
      },
    },
    created () {
      const _this=this;
      axios.get('http://localhost:8181/userDoingorders/'+_this.$store.getters.getUserId+'/1/1').then(function (resp) {
        console.log(resp)
        _this.tableData=resp.data.list
        _this.pageSize = resp.data.pageSize
        _this.total = resp.data.total
      })
    },

    data(){
      return{
        pageSize:1,
        total:1,
        cellectorname:'',
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
          phone:13615787610,
        }],
      }
    }
  }
</script>

<style scoped>

</style>
