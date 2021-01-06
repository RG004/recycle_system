<template>
  <div v-if="show">
    根据快递员姓名查询订单:<el-input v-model="userrequire.collectorname" placeholder="请输入快递员姓名" style="width: 200px"></el-input>
    根据日期查询订单：
    <el-select v-model="userrequire.datepick" placeholder="请选择">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-date-picker v-if="this.userrequire.datepick=='day'" v-model="userrequire.datebyday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd"></el-date-picker>
    <el-date-picker v-if="this.userrequire.datepick=='month'" v-model="userrequire.datebymonth" type="month" placeholder="选择月" format="yyyy年MM月" value-format="yyyy-MM"></el-date-picker>
    <el-button  type="primary" round  @click="findbyrequire">查询</el-button>
    <el-button  type="primary" round  @click="findall">查询全部</el-button>

    <el-table :data="tableData">
      <el-table-column prop="donateId" label="订单号" width="140">
      </el-table-column>
      <el-table-column prop="scheduledTime" label="预约时间" width="300">
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
            <div>{{scope.row.donateDetail}}</div>
            <el-button  type="primary" round slot="reference" >捐赠详情</el-button>
          </el-popover>
          <el-popover placement="right" width="400" trigger="click">
            <div>
              <div style="display: inline-block;">请选择上门地址：</div>
              <el-select style="width: 400px" v-model="donationform.addressId"  clearable placeholder="请选择捐赠地点">
                <el-option
                  v-for="item in addressList"
                  :key="item.addressId"
                  :label="item.addressDetails"
                  :value="item.addressId">
                </el-option>
              </el-select>
              <div class="block"  style="margin: 0 auto ;">
                <div class="demonstration" style="width: 128px;display: inline-block;">请选择上门时间:  </div>
                <el-date-picker
                  v-model="donationform.scheduledTime"
                  type="datetime"
                  value-format="yyyy-MM-dd hh:mm:ss"
                  placeholder="选择日期时间">
                </el-date-picker>
              </div>
              <div>
                <div>请填写捐赠物品详细信息：</div>
                <el-input type="textarea" :rows="5" v-model="donationform.donateDetail" ></el-input>
              </div>
              <div style="float: left;">
                <el-button style="margin-top: 12px;"  @click="Update(scope.row.donateId)" >确认修改</el-button>
              </div>
            </div>
            <el-button  type="primary" round slot="reference" @click="sure(scope.row.donateDetail,scope.row.scheduledTime)">修改捐赠物品</el-button>
          </el-popover>
          <el-button id="popo-btn" ref="popo" />

        </template>
      </el-table-column>
    </el-table>
    <el-pagination background layout="total, prev, pager, next, jumper" :page-size="pageSize" :total="total" @current-change="page"></el-pagination>
  </div>
</template>

<script>
  export default {
    methods:{
      sure(donateDetail,scheduledTime){
        this.donationform.donateDetail=donateDetail
        this.donationform.scheduledTime=scheduledTime
      },
      Update(donateId){
        this.donationform.donateId=donateId
        axios.post('http://localhost:8181/confirmdonation',this.donationform).then(function (resp) {
          console.log(resp)
        })
        this.$refs.popo.$el.click()
      },
      findbyrequire() {
        const _this = this
        axios.post('http://localhost:8181/userfinddoingdonate/1/8',this.userrequire).then(function (resp) {
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
        axios.post('http://localhost:8181/userfinddoingdonate/1/8',this.userrequire).then(function (resp) {
          _this.tableData=resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      page(currentPage) {
        const _this = this
        axios.post('http://localhost:8181/userfinddoingdonate/' + currentPage + '/8', _this.userrequire).then(function (resp) {
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      }
    },
    created () {
      const _this=this;
      axios.post('http://localhost:8181/userfinddoingdonate/1/8',this.userrequire).then(function (resp) {
        console.log(resp)
        _this.show=true
        _this.tableData=resp.data.list
        _this.pageSize = resp.data.pageSize
        _this.total = resp.data.total

      })
      axios.get('http://localhost:8181/userAlladdress/'+this.$store.getters.getUserId+'').then(function (resp) {
        console.log(resp)
        _this.addressList=resp.data.addressList
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
        addressList: [{
          addressId:1,
          addressDetails:'浙江省杭州市西湖区留和路288号浙江工业大学屏峰校区'
        }],
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
        donationform:{
          donateId:1,
          addressId:1,
          scheduledTime:'',
          donateDetail:''
        },
        tableData: [{
          donateId: 1,
          scheduledTime: '12月15日 下午17：00',
          finishedTime: '12月15日 下午17：10',
          collectorName: '陈南',
          evaluationId:'',
          phone: 13615787610,
          donateDetail:'',
        },
          {
            donateId: 2,
            scheduledTime: '12月15日 下午17：00',
            finishedTime:null,
            collectorName: '陈南',
            evaluationId:null,
            phone:13615787610,
            donateDetail:'',
          }]
      }
    }
  }
</script>

<style scoped>
  #popo-btn{
    width: 0;
    height: 0;
    border: none;
    padding: 0;
    margin: 0;
  }
</style>
