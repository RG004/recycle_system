<template>
  <div class="address">
    <el-cascader :props="props" style="width: 400px;margin-right: 20px" :key="modalKey" v-model="address.value" filterable></el-cascader>
    <el-input v-model="address.detail"></el-input>
    <br>
    <el-button @click="changeValue">提交</el-button>
    <el-button @click="changeValue2">提交2</el-button>
    {{address.value}}
  </div>
</template>

<script>
  export default {
    data() {
      return {
        props: {
          lazy: true,
          lazyLoad(node, resolve) {
            const { level } = node;
            let url =
              'https://restapi.amap.com/v3/config/district?parameters&key=8c922d0176df163a311ac3425db373c6';
              if (level > 0) {
              url = 'https://restapi.amap.com/v3/config/district?parameters&key=8c922d0176df163a311ac3425db373c6&keywords='+node.value+'&subdistrict=1';
            }
            level + 1;
            axios.get(url).then(res => {
              let nodes = res.data.districts[0].districts.map(item => {
                return {
                  label: item.name,
                  value: item.name,
                  leaf: level >= 3
                };
              });
              resolve(nodes);
            });
          }
        },
        address:{
          value: [],
          detail:'',
        },
        modalKey: 0
      };
    },

    methods: {
      changeValue() {
        axios.post('http://localhost:8181/insertAddress',this.address).then(function (resp){
        })
      },
      changeValue2() {
        console.log(this.address)
      }
    }
  };
</script>

<style scoped>

</style>



