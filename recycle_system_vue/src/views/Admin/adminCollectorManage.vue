<template>
  <div>
    <el-table :data="tableData" >
      <el-table-column align="center" prop="collectorPersonId" label="编号" width="170"></el-table-column>
      <el-table-column align="center" prop="collectorName" label="派送员姓名" width="170"></el-table-column>
      <el-table-column align="center" prop="userName" label="用户名" width="170"></el-table-column>
      <el-table-column align="center" prop="phone" label="电话号码" width="200"></el-table-column>
      <el-table-column align="center" prop="idcardNumber" label="身份证号" width="200"></el-table-column>
      <el-table-column align="center" prop="siteName" label="站点名称" width="170"></el-table-column>
      <el-table-column align="center" prop="userStatus" label="是否启用" >
        <template v-slot="scope">
          <el-switch v-model="scope.row.userStatus"
                     active-color="#13ce66"
                     active-value="T"
                     inactive-value="F"
                     @change="changeState(scope.row.collectorPersonId,scope.row.userStatus)"
          ></el-switch>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    created () {
      const _this=this
      axios.get('http://localhost:8181/allCollectorstatus').then(function (resp) {
        console.log(resp)
        _this.tableData=resp.data
      })
    },
    methods:{
      changeState(id,status){
        const _this = this
        axios.post('http://localhost:8181/changeStatus/'+id+'/'+status).then(function (resp) {
          console.log(resp)
        })
      }
    },
    data(){
      return{
        tableData:[{
          collectorPersonId:1,
          collectorName:'1',
          userName:'1',
          phone:'1',
          idcardNumber:'1',
          siteName:'1',
          userStatus:'1'
        }],
      }
    }
  }
</script>

<style scoped>
</style>
