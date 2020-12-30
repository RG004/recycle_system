<template>
  <div>
    <el-table :data="tableData">
      <el-table-column prop="itemTypeName" label="物品种类名字" width="300"></el-table-column>
      <el-table-column prop="itemName" label="物品名字" width="300"></el-table-column>
      <el-table-column prop="itemPrice" label="单价" width="300"></el-table-column>
      <el-table-column  fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button  type="primary" round slot="reference" icon="el-icon-edit-outline" style="background-color: #B3C0D1;
        border-color: #B3C0D1"  @click="edit(scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background layout="total, prev, pager, next, jumper" :page-size="pageSize" :total="total" @current-change="page"></el-pagination>
  </div>
</template>

<script>
  export default {
    created () {
      const _this=this
      axios.get('http://localhost:8181/allItem/1/5').then(function (resp) {
        console.log(resp)
        _this.tableData=resp.data.list
        _this.pageSize = resp.data.pageSize
        _this.total = resp.data.total
      })
    },
    methods:{
      edit(){

      }
    },
    data(){
      return{
        pageSize:1,
        total:1,
        tableData:[{
          itemId:1,
          itemTypeId:1,
          itemTypeName:'',
          itemName:'',
          itemPrice:1,
        }]
      }
    }
  }
</script>

<style scoped>

</style>
