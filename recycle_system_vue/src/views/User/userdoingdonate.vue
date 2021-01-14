<template>
  <div v-if="show">
    <div style="display: inline-block;margin-right: 6px;"><h1 class="title" style="width: 90%">根据快递员姓名查询订单:</h1></div>
    <el-input v-model="userrequire.collectorname" placeholder="请输入快递员姓名" style="width: 200px"></el-input>
    <div style="display: inline-block;margin-right: 6px;"><h1 class="title" >根据日期查询订单：</h1></div>
    <el-select v-model="userrequire.datepick" placeholder="请选择">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-date-picker v-if="this.userrequire.datepick=='day'" v-model="userrequire.datebyday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd"></el-date-picker>
    <el-date-picker v-if="this.userrequire.datepick=='month'" v-model="userrequire.datebymonth" type="month" placeholder="选择月" format="yyyy年MM月" value-format="yyyy-MM"></el-date-picker>
    <el-button  icon="el-icon-search"  @click="findbyrequire">查询</el-button>
    <el-button  icon="el-icon-search"  @click="findall">查询全部</el-button>

    <div class="user_skills" style="margin-top:20px;" >
    <el-table :data="tableData" :header-cell-style="{background:'transparent'}">
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
      <el-table-column fixed="right" label="操作" width="400">
        <template slot-scope="scope">
          <el-popover placement="right" width="400" trigger="click">
            <div>{{scope.row.donateDetail}}</div>
            <el-button  icon="el-icon-more-outline" round slot="reference" @click="selectdetail"></el-button>
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
            <el-button  icon="el-icon-edit-outline" round slot="reference" @click="sure(scope.row.donateDetail,scope.row.scheduledTime)"></el-button>
          </el-popover>
          <el-button  icon="el-icon-delete" round @click="Delete(scope.row.donateId)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    </div>
    <el-pagination   :background="isBackground"   layout="total, prev, pager, next, jumper" :page-size="pageSize" :total="total" @current-change="page"></el-pagination>
  </div>
</template>

<script>
  export default {
    methods:{
      Delete(donateId){
        const _this=this;
        axios.get('http://localhost:8181/userdeletedonation/'+donateId+'').then(function (resp) {
          console.log(resp)
          alert('删除成功')
        })
      },
      selectdetail(){
        const _this=this
        axios.post('http://localhost:8181/userfinddoingdonate/1/8',this.userrequire).then(function (resp) {
          console.log(resp)
          _this.tableData=resp.data.list
        })
      },
      sure(donateDetail,scheduledTime){
        this.donationform.donateDetail=donateDetail
        this.donationform.scheduledTime=scheduledTime
      },
      Update(donateId){
        const _this=this
        this.donationform.donateId=donateId
        axios.post('http://localhost:8181/confirmdonation',this.donationform).then(function (resp) {
          console.log(resp)
          if(resp.data){
            _this.$alert('捐赠订单已修改','消息',{
              confirmButtonText:'确定',
            })
          }
        })
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
        isBackground: true,
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
  /deep/ .el-input__inner{
    background-color: transparent;
  }
  * {
    background-color: transparent;
  }

  .user_skills /deep/  .el-table, .el-table__expanded-cell {
    background-color: transparent;
  }
  .user_skills /deep/ .el-table tr {
    background-color: transparent!important;
  }
  .user_skills /deep/  .el-table--enable-row-transition .el-table__body td, .el-table .cell{
    background-color: transparent;
  }
  /deep/ .el-pagination.is-background .el-pager li:not(.disabled).active {
    color: rgb(147,153,159);
    background-color: transparent;
  }
  /deep/ .el-pagination.is-background  .btn-next{
    background-color: transparent;
  }
  /deep/ .el-pagination.is-background  .btn-prev{
    background-color: transparent;
  }
  /deep/ .btn-prev{
    background-color: transparent;
  }
  .title{
    font-size: 15px;
    font-weight: 700;
    height: 26px;
    line-height: 26px;
    padding-left: 10px;
    margin-left: 12PX;
    color: rgb(147,153,159);
  }
</style>
