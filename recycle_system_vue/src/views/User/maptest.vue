<template>
  <div>
    <div>
      <el-input v-model="detail" placeholder="请输入地址"></el-input>
      <el-button @click="put">提交</el-button>
    </div>
    <div id="all-map" class="map"></div>
  </div>

</template>
<script>
  import AMap from 'AMap'
  export default {
    mounted(){
      this.GaodeMap()
    },
    methods: {
      put(){
        const _this = this
        axios.get('https://restapi.amap.com/v3/geocode/geo?address='+this.detail+'&key=8c922d0176df163a311ac3425db373c6').then(function (resp) {
          console.log(resp)
          _this.address=resp.data.geocodes[0].location
          _this.jing=_this.address.substr(0,10)
          _this.wei=_this.address.substr(11,10)
          _this.GaodeMap()
        })

      },
      GaodeMap () {
        let map = new AMap.Map('all-map', {
          center: [this.jing, this.wei],
          resizeEnable: true,
          zoom: 17
        });
        AMap.plugin(['AMap.ToolBar', 'AMap.Scale'], function () {
          map.addControl(new AMap.ToolBar())
          map.addControl(new AMap.Scale())
        })
        let infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
        //遍历生成多个标记点
          for (let i = 0, marker; i < this.lnglats.length; i++) {
            let marker = new AMap.Marker({
              position: [this.lnglats[i].j, this.lnglats[i].w],//不同标记点的经纬度
              map: map,
              icon:el-icon-menu
            });
            marker.content = this.lnglats[i].content;
            marker.on('click', markerClick);
            //marker.emit('click', {target: marker});//默认初始化不出现信息窗体,打开初始化就出现信息窗体
          }
          function markerClick(e) {
            infoWindow.setContent(e.target.content);
            infoWindow.open(map, e.target.getPosition());
          }
        },
      },
    data(){
      return{
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
      }
    }
  }
</script>

<style scoped>
  .map{
    width: 800px;
    height: 700px;
  }
</style>
