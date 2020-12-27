<template>
  <div>
    根据快递员姓名查询订单:<el-input v-model="collectorrequire.username" placeholder="请输入快递员姓名" style="width: 200px"></el-input>
    根据日期查询订单：
    <el-select v-model="collectorrequire.datepick" placeholder="请选择">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-date-picker v-if="this.collectorrequire.datepick=='day'" v-model="collectorrequire.datebyday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd"></el-date-picker>
    <el-date-picker v-if="this.collectorrequire.datepick=='month'" v-model="collectorrequire.datebymonth" type="month" placeholder="选择月" format="yyyy年MM月" value-format="yyyy-MM"></el-date-picker>
    <el-button  type="primary" round  @click="findbyusername">查询</el-button>
    <el-button  type="primary" round  @click="findall">查询全部</el-button>

    <el-table :data="tableData">
      <el-table-column prop="recycleOrderId" label="订单号" width="140">
      </el-table-column>
      <el-table-column prop="scheduledTime" label="预约时间" width="300">
      </el-table-column>
      <el-table-column  label="完成时间" width="300">
        <template slot-scope="scope">
          <span v-if="scope.row.finishedTime!=null">{{scope.row.finishedTime}}</span>
          <span v-else>未完成</span>
        </template>
      </el-table-column>
      <el-table-column prop="userName" label="用户" width="140">
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
      findbyusername () {
        const _this = this
        alert(this.collectorrequire.datebyday)
        this.selectbyrequire=true
        this.selectbynormal=false
        axios.post('http://localhost:8181/collectorFindordersByrequire/1/2',this.collectorrequire).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      findall(){
        const _this=this
        this.selectbyrequire=false
        this.selectbynormal=true
        this.collectorrequire.username=''
        this.collectorrequire.datebymonth=''
        this.collectorrequire.datebyday=''
        this.collectorrequire.datepick='day'
        axios.get('http://localhost:8181/collectorAllorders/'+_this.$store.getters.getCollectorId+'/1/2').then(function (resp) {
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
        if(this.selectbynormal){
          axios.get('http://localhost:8181/collectorAllorders/'+_this.$store.getters.getCollectorId+'/'+currentPage+'/2').then(function(resp){
            _this.tableData = resp.data.list
            _this.pageSize = resp.data.pageSize
            _this.total = resp.data.total
          })}else if(this.selectbyrequire){
            axios.post('http://localhost:8181/collectorFindordersByrequire/'+currentPage+'/2',_this.collectorrequire).then(function (resp) {
              _this.tableData = resp.data.list
              _this.pageSize = resp.data.pageSize
              _this.total = resp.data.total
            })
        }
      }
    },
    created () {
      const _this=this;
      axios.get('http://localhost:8181/collectorAllorders/'+_this.$store.getters.getCollectorId+'/1/2').then(function (resp) {
        _this.tableData=resp.data.list
        _this.pageSize = resp.data.pageSize
        _this.total = resp.data.total
      })
    },
    data(){
      return{
        pageSize:1,
        total:1,
        selectbyrequire:false,
        selectbynormal:true,
        collectorrequire:{
          id:this.$store.getters.getCollectorId,
          username:'',
          datebyday:'',
          datebymonth:'',
          datepick:'day',
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
          finishedTime: '12月15日 下午17：10',
          userName: '陈南',
          phone: 13615787610,
        },
          {
          recycleOrderId: 2,
          scheduledTime: '12月15日 下午17：00',
          finishedTime:'12月15日 下午17：10',
          userName: '陈南',
          phone:13615787610,
        }]
      }
    }
  }
</script>

<style scoped>

</style>
