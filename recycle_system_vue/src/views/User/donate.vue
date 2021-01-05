<template>
  <el-container direction="vertical">
  <div class="menu-wrapper" ref="menuWrapper">
    <el-menu class="el-menu-demo" mode="horizontal"  background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" style="width: 100%;">
      <el-menu-item>物资捐赠</el-menu-item>
    </el-menu>
  </div>
    <div>
      <div style="height: 550px;width:50%;float:left;padding-left: 0px;display: inline-block;"  class="foods-wrapper" ref="foodsWrapper">
        <div>
          <div style="display: inline-block;">请选择捐赠地址：</div>
          <el-select  style="width: 400px"v-model="form.helpId"  clearable placeholder="请选择捐赠地点">
            <el-option v-for="item in help_the_poor" :key="item.helpId" :label="item.helpName" :value="item.helpId"></el-option>
          </el-select>
        </div>
        <div>
          <div>{{this.help_the_poor[0].latitude }}</div>
          <div>请填写捐赠物品详细信息：</div>
          <el-input type="textarea" :rows="5" v-model="form.donateDetail"></el-input>
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
      <div style="display: inline-block;float: left;width: 50%;height: 550px" id="all-map"></div>
    </div>

  </el-container>
</template>

<script>
    import AMap from 'AMap'
    export default {
      data(){
        return {
          jing:120.047668,
          wei:30.234097,
          address:'',//经纬度
          detail:'',//地址信息
          lnglats: [{
            j:120.047668,
            w:30.234097,
            content:'zqy笨比'
          },{
            j:120.047668,
            w:30.235197,
            content:'zqy笨比'
          },{
            j:120.047668,
            w:30.236297,
            content:'zqy笨比'
          },{
            j:120.047668,
            w:30.237397,
            content:'zqy笨比'
          }
          ],
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
              helpId:2,
              helpName:'四川齿可波西乡布西村',
              longitude:11,
              latitude:111,
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
          _this.GaodeMap()
        })
      },
      methods: {
        finish(){
          const _this=this
          this.form.userId=this.$store.getters.getUserId;
          axios.post('http://localhost:8181/placeandonation',this.form).then(function (resp) {
            console.log(resp)
            if(resp.data){
              _this.$alert('捐赠成功','消息',{
                confirmButtonText:'确定',
                })
            }
          })
        },
        GaodeMap () {
          let map = new AMap.Map('all-map', {
            center: [this.help_the_poor[0].longitude, this.help_the_poor[0].latitude],
            resizeEnable: true,
            zoom: 4
          });
          AMap.plugin(['AMap.ToolBar', 'AMap.Scale'], function () {
            map.addControl(new AMap.ToolBar())
            map.addControl(new AMap.Scale())
          })
          let infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
          //遍历生成多个标记点
          for (let i = 0, marker; i < this.help_the_poor.length; i++) {
            let marker = new AMap.Marker({
              position: [this.help_the_poor[i].longitude, this.help_the_poor[i].latitude],//不同标记点的经纬度
              map: map,
            });
            marker.content = this.help_the_poor[i].helpName+" \n "+this.help_the_poor[i].helpDetail;
            marker.on('click', markerClick);
            //marker.emit('click', {target: marker});//默认初始化不出现信息窗体,打开初始化就出现信息窗体
          }
          function markerClick(e) {
            infoWindow.setContent(e.target.content);
            infoWindow.open(map, e.target.getPosition());
            map.setFitView(e.target);
          }
        },
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
