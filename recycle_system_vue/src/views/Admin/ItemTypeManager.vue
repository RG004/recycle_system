<template>
  <div>
    <el-table :data="ItemType">
      <el-table-column prop="itemTypeId" label="Id" width="300"></el-table-column>
      <el-table-column prop="itemTypeName" label="名字" width="300"></el-table-column>
      <el-table-column  fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button  type="primary" round slot="reference" icon="el-icon-edit-outline" style="background-color: #B3C0D1;
        border-color: #B3C0D1"  @click="editItemTypeName(scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="text-align: center; position:relative;">
      <el-button type="primary" @click="additemTypeName" icon="el-icon-plus" style ="background-color: #B3C0D1; border-color: #B3C0D1;margin-left: -600px"></el-button>
    </div>
    <el-dialog title="编辑物品种类" :visible.sync="editdialogFormVisible">
      <el-form :model="editform" @submit.native.prevent>
        <el-form-item label="物品种类" :label-width="formLabelWidth">
          <el-input v-model="editform.itemTypeName" auto-complete="off" @keyup.enter.native="update"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary"  @click="update">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="新增物品种类" :visible.sync="adddialogFormVisible">
      <!-- 在el-dialog中进行嵌套el-form实现弹出表格的效果 -->
      <el-form :model="addform" @submit.native.prevent>
        <el-form-item label="物品种类" :label-width="formLabelWidth">
          <el-input v-model="addform.itemTypeName" auto-complete="off" @keyup.enter.native="add"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="canceladd">取 消</el-button>
        <!-- 设置触发更新的方法 -->
        <el-button type="primary"  @click="add">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
  export default {
    created () {
      const _this =this
      axios.get('http://localhost:8181/allItemType').then(function (resp) {
        console.log(resp)
        _this.ItemType=resp.data
      })
    },
    methods:{
      editItemTypeName(row){
        this.editform.itemTypeName=row.itemTypeName
        this.editform.itemTypeId=row.itemTypeId
        this.editdialogFormVisible=true
      },
      update(){
        const _this=this
        this.editdialogFormVisible=false
        axios.post('http://localhost:8181/updateItemType',this.editform).then(function (resp) {
          for(var j=0,len=_this.ItemType.length;j<len;j++){
            if(_this.ItemType[j].itemTypeId==_this.editform.itemTypeId){
              _this.ItemType[j].itemTypeName=_this.editform.itemTypeName
            }
          }
        })
      },
      cancel(){
        this.editdialogFormVisible=false
      },
      additemTypeName(){

        this.adddialogFormVisible=true
        this.addform.itemTypeName=''
      },
      add(){
        const _this=this
        this.adddialogFormVisible=false
        axios.post('http://localhost:8181/insertItemType',this.addform).then(function (resp) {
          _this.ItemType.push(resp.data);
        })
      },
      canceladd(){
        this.adddialogFormVisible=false
      }
    },
    data(){
      return {
        ItemType:[{
          itemTypeId:'1',
          itemTypeName:'123',
        }],
        editform:{
          itemTypeId:'',
          itemTypeName:'',
        },
        formLabelWidth: "80px",
        editdialogFormVisible:false,
        adddialogFormVisible:false,
        addform:{
          itemTypeName:'',
        },
      }
    }
  }
</script>

<style scoped>

</style>
