<template>
  <div>
    <el-table :data="table">
        <el-table-column  :key="Math.random()" v-if="this.schduledTime=='123'" prop="scheduledTime" label="time" width="300">
        </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        table: [{
          scheduledTime: '123',
        },
          {
            scheduledTime: '1234',
          },
          {
            scheduledTime: '1234',
          },
          {
            scheduledTime: '1234',
          },
          {
            scheduledTime: '123',
          }
        ],
      }
    },
    // created () {
    //   const  _this=this
    //   axios.get('http://localhost:8181/userAllorders/'+_this.$store.getters.getUserId+'/1/2').then(function (resp) {
    //     console.log(resp)
    //     _this.table=resp.data.list
    //     for(var j=0,len=_this.table.length;j<len;j++){
    //       _this.table[j].scheduledTime=_this.table[j].scheduledTime.substr(0,10)
    //     }
    //   })

    // }
  }
</script>

<style scoped>

</style>



