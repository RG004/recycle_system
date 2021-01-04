<template>
  <div>
    根据快递员姓名查询订单:<el-input v-model="username" placeholder="请输入用户名" style="width: 200px"></el-input>
    <el-button  type="primary" round  @click="findbyusername">查询</el-button>
    <el-button  type="primary" round  @click="findall">查询全部</el-button>
    <el-table :data="tableData">
      <el-table-column prop="recycleOrderId" label="订单号" width="140">
      </el-table-column>
      <el-table-column prop="scheduledTime" label="预约时间" width="300">
      </el-table-column>
      <el-table-column prop="userName" label="配送员" width="140">
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
          <el-button  type="primary" round @click="jumpConfirm(scope.row.recycleOrderId)">确认订单</el-button>
        </template>

      </el-table-column>
    </el-table>
    <el-pagination background layout="total, prev, pager, next, jumper" :page-size="pageSize" :total="total" @current-change="page"></el-pagination>
  </div>
</template>
<script>
  export default {
    methods: {
      jumpConfirm(recycleOrderId){
        this.$router.push({
          path: "/confirmorder",
          query: {recycleOrderId: recycleOrderId }
        });
      },
      findbyusername () {
        const _this = this
        this.selectbyusername = true
        this.selectbynormal = false
        if(this.username!=''){
          axios.get('http://localhost:8181/collectorDoingordersByusername/' + _this.$store.getters.getCollectorId + '/' + this.username + '/1/1').then(function (resp) {
            _this.tableData = resp.data.list
            _this.pageSize = resp.data.pageSize
            _this.total = resp.data.total
          })
        }
        else{
          axios.get('http://localhost:8181/collectorDoingorders/' + _this.$store.getters.getCollectorId + '/1/1').then(function (resp) {
            _this.tableData = resp.data.list
            _this.pageSize = resp.data.pageSize
            _this.total = resp.data.total
          })
        }
      },
      findall () {
        const _this = this
        this.selectbynormal = true
        this.selectbyusername = false
        this.username = ''
        axios.get('http://localhost:8181/collectorDoingorders/' + _this.$store.getters.getCollectorId + '/1/1').then(function (resp) {
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      getDetail (recycleOrderId) {
        const _this = this
        axios.get('http://localhost:8181/OrdersDetail/' + recycleOrderId + '').then(function (resp) {
          _this.recycleOrdersDetailVoList = resp.data
          for (var j = 0, len = _this.recycleOrdersDetailVoList.length; j < len; j++) {
            _this.recycleOrdersDetailVoList[j].sum = _this.recycleOrdersDetailVoList[j].quantity * _this.recycleOrdersDetailVoList[j].itemPrice
          }

        })
      },
      page (currentPage) {
        const _this = this
        if (this.selectbynormal) {
          axios.get('http://localhost:8181/collectorDoingorders/' + _this.$store.getters.getCollectorId + '/' + currentPage + '/1').then(function (resp) {
            _this.tableData = resp.data.list
            _this.pageSize = resp.data.pageSize
            _this.total = resp.data.total
          })
        } else if (this.selectbyusername) {
          axios.get('http://localhost:8181/collectorDoingordersByusername/' + _this.$store.getters.getCollectorId + '/' + username + '/' + currentPage + '/1').then(function (resp) {
            _this.tableData = resp.data.list
            _this.pageSize = resp.data.pageSize
            _this.total = resp.data.total
          })
        }
      },
    },
      created () {
        const _this = this;
        axios.get('http://localhost:8181/collectorDoingorders/' + _this.$store.getters.getCollectorId + '/1/1').then(function (resp) {
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      data () {
        return {
          pageSize: 1,
          total: 1,
          username: '',
          selectbyusername: false,
          selectbynormal: true,
          recycleOrdersDetailVoList: [{
            itemName: '纸板',
            quantity: 10,
            itemPrice: 1,
            sum: '',
          }, {
            itemName: '易拉罐',
            quantity: 20,
            itemPrice: 0.1,
            sum: '',
          }, {
            itemName: '啤酒瓶',
            quantity: 5,
            itemPrice: 1,
            sum: '',
          }, {
            itemName: '旧衣服',
            quantity: 20,
            itemPrice: 0.5,
            sum: '',
          }],
          tableData: [{
            recycleOrderId: 1,
            scheduledTime: '12月15日 下午17：00',
            userName: '陈南',
            phone: 13615787610,
          }],
        }
      }
  }
</script>

<style scoped>

</style>
