<template>
  <div class="block">
    <el-carousel :interval="4000" type="card" height="300px">
      <el-carousel-item v-for="item in imagebox" :key="item.id" >
        <img :src="item.idView" class="image">
      </el-carousel-item>
    </el-carousel>
    <div style="display: flex">
<!--      左侧-->
      <div>
        <div style="display: flex;margin:30px 0px" >
          <el-card class="box-card" style="margin-right: 60px;margin-left: 60px">
            <div slot="header" class="clearfix">
              <span>我的订单</span>
              <el-button style="float: right; padding: 3px 0" type="text"  @click="doingInorder">查看</el-button>
            </div>
            您有{{ count }}个正在进行的订单


          </el-card>

          <el-button style="width: 200px;height: 120px; box-shadow: 0px 1px 5px #cbcaca; " @click="recycle">查看评价</el-button>
        </div>

        <el-card class="box-card" style="margin-left: 60px; width: 740px;height: 180px">
            <div slot="header" class="clearfix">
            <span>回收流程</span>
            </div>
            <div style="text-align: center">
              <i class="el-icon-phone-outline" ></i>
              <i class="el-icon-arrow-right" ></i>
              <i class="el-icon-refresh" ></i>
              <i class="el-icon-arrow-right" ></i>
              <i class="el-icon-circle-check" ></i>
              <i class="el-icon-arrow-right" ></i>
              <i class="el-icon-chat-dot-square" ></i>
            </div>
            <pre style="font-size: 15px">        联系客户             上门回收              确认订单             查看评价
        </pre>
        </el-card>
      </div>
<!--      右侧-->
      <div>

        <el-card class="box-card" style="width: 390px ;margin:30px 40px">
          <div slot="header" class="clearfix">
            <span>取件小贴士</span>
          </div>
          <div  class="clearfix">
            <p><i class="el-icon-question"></i>1.电话约定</p>
            <p><i class="el-icon-s-opportunity"></i>在准备去下一客户家回收废品时，最好提前拨通客户电话约定收件时间，避免出现到达客户门口的时候客户却不在家的问题，这样会浪费大量的时间。</p>
            <el-divider></el-divider>
            <p><i class="el-icon-question"></i>2.收件时注意事项</p>
            <p><i class="el-icon-s-opportunity"></i>(1)验货是否是违禁品。(2)投递地址是否在公司业务覆盖范围内。(3)单据必填部分须书写清晰，认真填写。(4)随身携带防水袋、信封等，对于有特殊要求的根据实际情况处理。(5)单据须黏贴到袋子封口的位置上。</p>

          </div>
        </el-card>
      </div>
    </div>


  </div>

</template>

<script>
export default {
  name: 'index',
  created () {
    const _this=this
    axios.get('http://localhost:8181/collectorDoingorders/' + _this.$store.getters.getCollectorId + '/1/4').then(function(resp){
      _this.count = resp.data.total
    })
  },
  data(){
    return{
      count:3,
      tableData: [{
        id: 1304,
        booktime: '12月15日 下午17：00',
        collector: '陈南',
        phone:13615787610
      }, {
        id: 1567,
        booktime: '12月15日 下午17：00',
        collector: '陈大南',
        phone:13615787610
      }, {
        id: 3456,
        booktime: '12月15日 下午17：00',
        collector: '陈小南',
        phone:13615787610
      }],
      imagebox:[{
        id:0,
        idView:require('../../assets/imagebox/1.png')
      },{
        id:1,
        idView:require('../../assets/imagebox/2.png')
      },{
        id:2,
        idView:require('../../assets/imagebox/3.png')
      },{
        id:3,
        idView:require('../../assets/imagebox/4.png')
      }

      ]
    }
  },
  methods: {
    doingInorder () {
      this.$router.push('/collectordoingInorder')
    },
    recycle () {
      this.$router.push('/collectordoinginorder')
    }

  }
}
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 480px;
}
.el-icon-phone-outline,.el-icon-refresh,.el-icon-circle-check,.el-icon-chat-dot-square{
  font-size: 40px;margin-right: 45px
}
.el-icon-arrow-right{
  font-size: 20px;margin-right: 45px;
}
.el-icon-question{
  margin-right: 10px;
  font-size: 18px;
  color: #6dbf6d;
}
.el-icon-s-opportunity{
  margin-right: 10px;
  font-size: 18px;
  color: #fa914b;
}


</style>
