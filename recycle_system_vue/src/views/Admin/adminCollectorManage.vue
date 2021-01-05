<template>
  <div>
    <el-table :data="tableData" >
      <el-table-column align="center" prop="collectorPersonId" label="编号" width="100"></el-table-column>
      <el-table-column align="center" prop="collectorName" label="派送员姓名" width="100"></el-table-column>
      <el-table-column align="center" prop="userName" label="用户名" width="100"></el-table-column>
      <el-table-column align="center" prop="phone" label="电话号码" width="200"></el-table-column>
      <el-table-column align="center" prop="idcardNumber" label="身份证号" width="200"></el-table-column>
      <el-table-column align="center" prop="siteName" label="站点名称" width="150"></el-table-column>
      <el-table-column align="center" prop="userStatus" label="是否启用">
        <template v-slot="scoped">
          <el-switch v-model="scoped.row.status"
                     active-color="#13ce66"
                     active-value="T"
                     inactive-value="F"
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
    data(){
      return{
        tableData:[{
          collectorPersonId:1,
          collectorName:'1',
          userName:'1',
          phone:'1',
          idcardNumber:'1',
          siteName:'1',
          userStatus:'1',
        }],
      }
    }
  }
</script>

<style scoped>
</style>
