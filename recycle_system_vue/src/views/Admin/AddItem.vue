<template>
  <el-form  :model="editform">
    <el-form-item label="种类">
      <el-select v-model="editform.itemTypeId" placeholder="请选择种类">
        <el-option v-for='item in option' :key="item.itemTypeId" :value='item.itemTypeId' :label='item.itemTypeName'></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="物品" prop="itemName">
      <el-input v-model="editform.itemName" ></el-input>
    </el-form-item>
    <el-form-item label="单价" prop="itemPrice">
      <el-input v-model="editform.itemPrice"></el-input>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    methods:{

    },
    created () {
      const _this=this
      axios.get('http://localhost:8181/allItemType').then(function (resp) {
        _this.option=resp.data
      })
    },
    data(){
      return{
        editform:{
          itemTypeId:'',
          itemName:'',
          itemPrice:'',
          itemPic:'',
        },
        option:[{
          itemTypeId:1,
          itemTypeName:'钢铁'
        },
        ],
      }
    }
  }
</script>

<style scoped>

</style>
