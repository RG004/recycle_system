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
      <el-table-column prop="recycleOrderId" label="订单号" width="80">
      </el-table-column>
      <el-table-column prop="scheduledTime" label="预约时间" width="200">
      </el-table-column>
      <el-table-column prop="totalAmount" label="总价" width="100">
      </el-table-column>
      <el-table-column prop="username" label="用户" width="200">
      </el-table-column>
      <el-table-column label="派送员" width="200">
        <template slot-scope="scope" >
          <span v-if="scope.row.collectorName!=null">{{scope.row.collectorName}}</span>
          <span v-else><el-button  type="primary" round @click="edit(scope.row)">选择派送员</el-button></span>
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
            <el-button  type="primary" round slot="reference" @click="getDetail(scope.row.recycleOrderId)" >查询订单详情</el-button>
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
    methods: {
      edit(row){
        this.editForm.recycleOrderId=row.recycleOrderId
        this.editForm.collectorName=''
        this.editVisible=true
      },
      closeDialog(){
        this.editVisible=false
      },
      submit(){
        const _this=this
        axios.get('http://localhost:8181/placecollector/'+this.editForm.recycleOrderId+'/'+this.editForm.collectorName+'').then(function (resp) {
          for(var j=0,len=_this.tableData.length;j<len;j++){
            if(_this.tableData[j].recycleOrderId==resp.data){
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
        axios.post('http://localhost:8181/adminfindAllDoingOrders/1/3',this.adminrequire).then(function (resp) {
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
        axios.post('http://localhost:8181/adminfindAllDoingOrders/1/3',this.adminrequire).then(function (resp) {
          _this.tableData=resp.data.list
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
        axios.post('http://localhost:8181/adminfindAllDoingOrders/'+currentPage+'/3',this.adminrequire).then(function (resp) {
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      }
    },
      created () {
        const _this = this;
        axios.post('http://localhost:8181/adminfindAllDoingOrders/1/3',this.adminrequire).then(function (resp) {
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
          _this.show=true
        })
        axios.get('http://localhost:8181/allCollector').then(function (r) {
          console.log(r)
          _this.collector=r.data
        })
      },
      data () {
        return {
          show:false,
          editVisible:false,
          editForm:{
            recycleOrderId:1,
            collectorName:'',
          },
          pageSize: 1,
          total: 1,
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
            addressDetails:'',
            username: '陈南',
            totalAmount:'',
            collectorName: '陈南',
          }],
        }
      }
  }
</script>

<style scoped>

</style>
