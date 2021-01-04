<template>
  <el-container direction="vertical">
  <div class="menu-wrapper" ref="menuWrapper">
    <el-menu class="el-menu-demo" mode="horizontal"  background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" style="width: 100%;">
      <el-menu-item>物资捐赠</el-menu-item>
    </el-menu>
  </div>
    <div>
      <div style="height: 550px;width:50%;float:left;padding-left: 0px;display: inline-block;"  class="foods-wrapper" ref="foodsWrapper">
        <!--    <ul style="list-style:none;">-->
        <!--      <div v-for="item in help_the_poor" class="food-item" :key="item.helpId" style="display: flex;flex-direction: column;">-->
        <!--        <h1 class="title" style="display: flex;flex-direction: column;">{{item.helpName}}</h1>-->
        <!--        <div class="content" style="display: flex;flex-direction: column;">{{item.helpDetail}}</div>-->
        <!--      </div>-->
        <!--    </ul>-->
        <div>
          <div style="display: inline-block;">请选择捐赠地址：</div>
          <el-select  style="width: 400px"v-model="form.helpId"  clearable placeholder="请选择捐赠地点">
            <el-option
              v-for="item in help_the_poor"
              :key="item.helpId"
              :label="item.helpName"
              :value="item.helpId">
            </el-option>
          </el-select>
        </div>
        <div>
          <div>请填写捐赠物品详细信息：</div>
          <el-input type="textarea" :rows="5" v-model="form.donateDetail"></el-input>
          <div>{{form.donateDetail}}</div>
        </div>
        <div class="block"  style="margin: 0 auto ;">
          <div class="demonstration" style="width: 128px;display: inline-block;">请选择上门时间:  </div>
          <el-date-picker
            v-model="form.scheduledTime"
            type="datetime"
            value-format="yyyy-MM-dd hh:mm:ss"
            placeholder="选择日期时间">
          </el-date-picker>
        </div>
        <div>
          <div style="display: inline-block;">请选择上门地址：</div>
          <el-select style="width: 400px" v-model="form.addressId"  clearable placeholder="请选择捐赠地点">
            <el-option
              v-for="item in addressList"
              :key="item.addressId"
              :label="item.addressDetails"
              :value="item.addressId">
            </el-option>
          </el-select>
        </div>
        <div style="float: left;">
          <el-button style="margin-top: 12px;"  @click="finish" >确认捐赠</el-button>
        </div>
      </div>
      <div style="display: inline-block;float: left;width: 50%;height: 550px">

      </div>

    </div>

  </el-container>
</template>

<script>
    export default {
      data(){
        return {
          form:{
            helpId:1,
            userId:1,
            scheduledTime:'',
            donateDetail:'',
            addressId:1,
          },
          addressList: [{
            addressId:1,
            addressDetails:'浙江省杭州市西湖区留和路288号浙江工业大学屏峰校区'
          }],
          help_the_poor:[
            {
            helpId:1,
            helpName:'四川齿可波西乡布西村',
            Longitude:11,
            Latitude:111,
            helpDetail:'这里的人们的生活都不富裕，家家户户都有好几个孩子，父母为了谋生外出打工，大多是留守儿童和老人在家，因为封建思想，有的孩子还早早地就结了婚，冬天很冷，孩子们的脸和手脚都被冻得起了冻疮，真的很不容易，希望能够得到大家的帮助。',
            helpStatus:''
            },
            {
              helpId:2,
              helpName:'asdfsadf',
              Longitude:11,
              Latitude:111,
              helpDetail:'这里的人们的生活都不富裕，家家户户都有好几个孩子，父母为了谋生外出打工，大多是留守儿童和老人在家，因为封建思想，有的孩子还早早地就结了婚，冬天很冷，孩子们的脸和手脚都被冻得起了冻疮，真的很不容易，希望能够得到大家的帮助。',
              helpStatus:''
            },{
              helpId:3,
              helpName:'四dfdfdfdf',
              Longitude:11,
              Latitude:111,
              helpDetail:'这里的人们的生活都不富裕，家家户户都有好几个孩子，父母为了谋生外出打工，大多是留守儿童和老人在家，因为封建思想，有的孩子还早早地就结了婚，冬天很冷，孩子们的脸和手脚都被冻得起了冻疮，真的很不容易，希望能够得到大家的帮助。',
              helpStatus:''
            },
            {
              helpId:4,
              helpName:'四川sdfsdfs西村',
              Longitude:11,
              Latitude:111,
              helpDetail:'这里的人们的生活都不富裕，家家户户都有好几个孩子，父母为了谋生外出打工，大多是留守儿童和老人在家，因为封建思想，有的孩子还早早地就结了婚，冬天很冷，孩子们的脸和手脚都被冻得起了冻疮，真的很不容易，希望能够得到大家的帮助。',
              helpStatus:''
            },
          ],
        }
      },
      created(){
        const _this=this;
        axios.get('http://localhost:8181/userAlladdress/'+this.$store.getters.getUserId+'').then(function (resp) {
          console.log(resp)
          _this.addressList=resp.data.addressList
        })
        axios.get('http://localhost:8181/userAllHelp').then(function (resp) {
          console.log(resp)
          _this.help_the_poor=resp.data
        })
      },
      methods: {
        finish(){
          this.form.userId=this.$store.getters.getUserId;
          axios.post('http://localhost:8181/placeandonation',this.form).then(function (resp) {
            console.log(resp)
          })
          // this.$router.push({
          //   path:'/userdoingindonate'
          // })

        }
      }
    }
</script>

<style scoped>
.foods-wrapper{
width: 50%;
}
.food-item{
  display: flex;
  margin: 18px;
  padding-bottom: 18px;
  border:rgba(7,17,27,.1) 1PX solid;
}
</style>
