<template>
  <div>
        <div style="text-align: center; position:absolute;top:20px;z-index: 1;">
          <el-button type="primary" @click="add" icon="el-icon-plus" style ="background-color: #B3C0D1; border-color: #B3C0D1;"></el-button>
        </div>
    <el-table :data="itemList">
      <el-table-column label="序号" width="150">
        <template slot-scope="scope">
          <span>{{scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="itemTypeName" label="种类" width="200">
      </el-table-column>
      <el-table-column prop="itemName" label="物品" width="200">
      </el-table-column>
      <el-table-column prop="itemPrice" label="单价（元/斤）" width="200">
      </el-table-column>
      <el-table-column  prop="image" label="废品图片" width="200">
        <template slot-scope="scope">
          <el-image style="width: 30px; height: 30px" :src="scope.row.image" :preview-src-list="[scope.row.image]">
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </template>
      </el-table-column>
      <el-table-column  fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button  type="primary" round slot="reference" icon="el-icon-edit-outline" style="background-color: #B3C0D1;
        border-color: #B3C0D1"  @click="modifyData(scope.$index, scope.row)"></el-button>
          <el-button  type="primary" round icon="el-icon-delete" style="background-color: #B3C0D1; border-color: #B3C0D1" slot="reference" @click="handleDelete(scope.$index, scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
<!--    <div style="text-align: center; position:relative;">-->
<!--      <el-button type="primary" @click="add" icon="el-icon-plus" style ="background-color: #B3C0D1; border-color: #B3C0D1;"></el-button>-->
<!--    </div>-->

    <el-dialog title="新增废品种类" :visible.sync="dialogFormVisible">
      <!-- 在el-dialog中进行嵌套el-form实现弹出表格的效果 -->
      <el-form :model="form" @submit.native.prevent>
        <el-form-item label="种类" :label-width="formLabelWidth">
<!--          <el-input v-model="form.type" auto-complete="off" @keyup.enter.native="update"></el-input>-->
          <el-select v-model="form.itemTypeId" placeholder="请选择种类">
            <el-option v-for='item in option' :key="item.itemTypeId" :value='item.itemTypeId' :label='item.itemTypeName'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="物品" :label-width="formLabelWidth">
          <el-input v-model="form.itemName" auto-complete="off" @keyup.enter.native="update"></el-input>
        </el-form-item>
        <el-form-item label="单价" :label-width="formLabelWidth">
          <el-input v-model="form.itemPrice" auto-complete="off" @keyup.enter.native="update"></el-input>
        </el-form-item>
        <el-form-item label="废品图片" :label-width="formLabelWidth">
          <el-input v-model="form.image" auto-complete="off" @keyup.enter.native="update"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <!-- 设置触发更新的方法 -->
        <el-button type="primary"  @click="update">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="centerDialogVisible" title="修改废品信息">
      <el-form  :model="editForm" @submit.native.prevent>
<!--        <el-form-item label="种类" prop="alttype">-->
<!--          <el-input v-model="editForm.alttype"  @keyup.enter.native="sumbitEditRow()"></el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="种类" :label-width="formLabelWidth">
          <!--          <el-input v-model="form.type" auto-complete="off" @keyup.enter.native="update"></el-input>-->
          <el-select v-model="editForm.alttype" placeholder="请选择种类">
            <el-option v-for='item in option' :key="item.itemTypeId" :value='item.itemTypeId' :label='item.itemTypeName'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="物品" prop="altitem">
          <el-input v-model="editForm.altitem"  @keyup.enter.native="submitEditRow()"></el-input>
        </el-form-item>
        <el-form-item label="单价" prop="altprice">
          <el-input v-model="editForm.altprice"  @keyup.enter.native="submitEditRow()"></el-input>
        </el-form-item>
        <el-form-item label="废品图片" prop="altimage">
          <el-input v-model="editForm.altimage"  @keyup.enter.native="submitEditRow()"></el-input>
        </el-form-item>
      </el-form>
      <div>
        <el-button @click="closeDialog()">取消</el-button>
        <el-button type="primary"  @click="submitEditRow()">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  let _index,itemTypeName;
  export default {

    created () {
      const _this=this
      axios.get('http://localhost:8181/Item').then(function (resp) {
        console.log(resp)
        _this.itemList=resp.data
      })
    },
    data() {
      let letterRule = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('输入内容不能为空'));
        } else{
          callback();
        }
      };

      return {
        option:[{
          itemTypeId:1,
          itemTypeName:'钢铁'
        },
          {
            itemTypeId:2,
            itemTypeName:'电子设备'
          },
          {
            itemTypeId:3,
            itemTypeName:'有毒垃圾'
          }

        ],

        itemList: [{
          itemTypeId:1,
          itemTypeName:'塑料',
          itemId:1,
          itemName:'塑料瓶',
          itemPrice:5,
          image:require("@/assets/imagebox/plasticbottle.jpg")
        }],
        centerDialogVisible: false,
        dialogFormVisible: false,
        formLabelWidth: "80px",
        // 设置form用于进行添加的时候绑定值
        form: {},
        value6: "",
        currentPage3: 1,
        currentIndex: "",
        editDialogVisible: false,
        editForm: {
          alttype:'',
          altitem:'',
          altprice:'',
          altimage:''
        },
      }
    },
    methods: {
      handleDelete (index, row) {
        // 设置类似于console类型的功能
        this.$confirm("确定删除该废品?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            // 移除对应索引位置的数据，可以对row进行设置向后台请求删除数据
            axios.post('http://localhost:8181/deleteItem',row).then(function (resp){
              console.log(resp);
            })
            this.itemList.splice(index, 1);
            this.$message({
              type: "success",
              message: "删除成功!"
            });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });
      },
      modifyData(index, row) {
        this.centerDialogVisible = true
        this.editForm.alttype = row.itemTypeName;
        itemTypeName=row.itemTypeName;
        this.editForm.altitem = row.itemName;
        this.editForm.altprice = row.itemPrice;
        this.editForm.altimage = row.image;//重置对象
        _index = index;
      },
      submitEditRow() {
        let editData = _index;
        if(itemTypeName!==this.editForm.alttype)
        {this.itemList[editData].itemTypeId = this.editForm.alttype;}
        this.itemList[editData].itemName = this.editForm.altitem;
        this.itemList[editData].itemPrice = this.editForm.altprice;
        this.itemList[editData].image = this.editForm.altimage;
        axios.post('http://localhost:8181/updateItem',this.itemList[editData]).then(function (resp){
          console.log(resp);
        })
        this.centerDialogVisible = false;
        location. reload()
      },
      closeDialog(){
        this.centerDialogVisible=false
        console.log("editfrom",this.editForm)
      },

      add() {
        this.form = {
          itemTypeId: "",
          itemName: "",
          itemPrice: "",
          image: ""
        };
        //   设置点击按钮之后进行显示对话框
        this.dialogFormVisible = true;
      },
      update() {
        axios.post('http://localhost:8181/insertItem',this.form).then(function (resp){
          console.log(resp);
          // this.itemList.push(this.form);
        })
        this.dialogFormVisible = false;
        location. reload()
      },
      cancel() {
        // 取消的时候直接设置对话框不可见即可
        this.dialogFormVisible = false;
      }
    }
  }
</script>

<style scoped>
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
    width: 1270px;
  }
</style>
