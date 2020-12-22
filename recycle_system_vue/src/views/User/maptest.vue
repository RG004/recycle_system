<template>
  <div>
    <div>
      <el-input v-model="address" placeholder="请输入地址"></el-input>
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
        axios.get('https://restapi.amap.com/v3/geocode/geo?address='+this.address+'&key=8c922d0176df163a311ac3425db373c6').then(function (resp) {
          console.log(resp)
          _this.address=resp.data.geocodes[0].location
          _this.jing=_this.address.substr(0,10)
          _this.wei=_this.address.substr(11,10)
          _this.GaodeMap()
        })

      },
      GaodeMap () {
        var map = new AMap.Map('all-map', {
          center: [this.jing,this.wei],
          resizeEnable: true,
          zoom: 15
        });
        AMap.plugin(['AMap.ToolBar', 'AMap.Scale'], function () {
          map.addControl(new AMap.ToolBar())
          map.addControl(new AMap.Scale())
        })

      },
    },
    data(){
      return{
        jing:120.047668,
        wei:30.234097,
        address:[120.047668,30.234097],
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
