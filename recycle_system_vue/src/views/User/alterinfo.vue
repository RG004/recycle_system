<template>
  <div>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>个人信息</span>
    </div>
    <div class="text item">
      <div style="margin: 20px">
      <div>ID：{{userId}}</div><div style="position: relative; top:-18px;left:600px">用户名：{{userName}}</div>
      </div>
      <div style="margin: 20px">
      <div>真实姓名：{{userRealname}}</div><div style="position: relative; top:-18px;left:600px">手机号：{{phone}}</div>
        <div style="position: relative; top:-38px;left:-430px"><el-button style="float: right; padding: 3px 0"  icon="el-icon-edit-outline" @click="modifyPhone(phone)"></el-button></div>
      </div>
    </div>
  </el-card>
  <el-table :data="addressList">
    <el-table-column label="序号" width="375">
      <template slot-scope="scope">
        <span>{{scope.$index + 1}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="addressDetails" label="地址" width="600">
    </el-table-column>
    <el-table-column  fixed="right" label="操作">
      <template slot-scope="scope">
        <el-button  type="primary" round slot="reference" icon="el-icon-edit-outline" style="background-color: #B3C0D1;
        border-color: #B3C0D1"  @click="modifyData(scope.$index, scope.row)"></el-button>
        <el-button  type="primary" round icon="el-icon-delete" style="background-color: #B3C0D1; border-color: #B3C0D1" slot="reference"
                    @click="handleDelete(scope.$index, scope.row)"></el-button>
      </template>
    </el-table-column>
  </el-table>
    <div style="text-align: center; position:relative;">
      <el-button type="primary" @click="add" icon="el-icon-plus" style ="background-color: #B3C0D1; border-color: #B3C0D1;"></el-button>
    </div>

    <el-dialog title="新增地址" :visible.sync="dialogFormVisible">
      <!-- 在el-dialog中进行嵌套el-form实现弹出表格的效果 -->
      <el-form :model="form" @submit.native.prevent>
        <el-form-item label="地址" :label-width="formLabelWidth">
          <el-input v-model="form.addressDetails" auto-complete="off" @keyup.enter.native="update"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <!-- 设置触发更新的方法 -->
        <el-button type="primary"  @click="update">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="centerDialogVisible">
      <el-form  :model="editForm" @submit.native.prevent>
        <el-form-item label="修改地址">
          <el-input v-model="editForm.addressDetails"  @keyup.enter.native="sumbitEditRow()"></el-input>
        </el-form-item>
      </el-form>
      <div>
        <el-button @click="closeDialog()">取消</el-button>
        <el-button type="primary"  @click="sumbitEditRow()">确定</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="editPhoneVisible">
      <el-form  :model="editForm" :rules="rules" ref="editForm" @submit.prevent.native >
        <el-form-item label="修改手机号码"  prop="phone2">
          <el-input v-model="editForm.phone2" @keyup.enter.native="sumbitEditPhone('editForm')"></el-input>
        </el-form-item>
        <div>
          <el-button @click="closePhoneDialog()">取消</el-button>
          <el-button type="primary" @click="sumbitEditPhone('editForm')">确定</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  let _index;
  export default {

    created () {
      const _this=this
      axios.get('http://localhost:8181/userAlladdress/'+this.$store.getters.getUserId+'').then(function (resp) {
        console.log(resp)
        _this.userId=resp.data.userId
        _this.userName=resp.data.userName
        _this.userRealname=resp.data.userRealname
        _this.phone=resp.data.phone
        _this.addressList=resp.data.addressList
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

          userId:1,
          userName:'yxy',
          userRealname:'杨昕语',

          phone:13615787610,
          addressList: [{
            addressDetails:'浙江省杭州市西湖区留和路288号浙江工业大学屏峰校区'
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
            phone2:''
          },
          editPhoneVisible:false,
          rules:{
            phone2:[
              { required: true, message: '请输入密码', trigger: 'blur' },
              { min: 11, max: 11, message: '长度需为11', trigger: 'blur' },
              { validator: letterRule, trigger: 'blur' }
            ]
          }
      }
    },
    methods: {
      modifyPhone(phone) {
        //初始化editForm中的phone2
        this.editForm.phone2=phone
        this.editPhoneVisible = true

      },
      sumbitEditPhone(formName){
        //改变editForm中的phone2
        const _this=this
        this.$refs[formName].validate((valid) => {//检验手机
          if (valid) {
            this.phone = this.editForm.phone2;
            axios.post('http://localhost:8181/userupdatePhone/'+this.userId+'/'+this.phone+'').then(function (resp) {
              console.log(resp)
            })

            this.editPhoneVisible = false;
          }else {
            _this.$alert('手机输入格式错误','提示')
          }
          }
        )
      },
      closePhoneDialog(){
        this.editPhoneVisible = false;
      },

      handleDelete (index, row) {
        // 设置类似于console类型的功能
        this.$confirm("确定删除该地址?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            // 移除对应索引位置的数据，可以对row进行设置向后台请求删除数据
            this.addressList.splice(index, 1);
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
        this.editForm = row;//重置对象
        _index = index;
      },
      sumbitEditRow() {
        let editData = _index;
        this.addressList[editData].addressDetails = this.editForm.addressDetails;
        this.centerDialogVisible = false;
      },
      closeDialog(){
        this.centerDialogVisible=false
        console.log("editfrom",this.editForm)
      },

      add() {
        this.form = {
          date: "",
          name: "",
          region: "",
          address: ""
        };
        //   设置点击按钮之后进行显示对话框
        this.dialogFormVisible = true;
      },
      update() {
        //   this.form.date = reformat(this.form.date);
        //    可以在html上面进行设置日期的格式化
        //   将我们添加的信息提交到总数据里面
        this.addressList.push(this.form);
        this.dialogFormVisible = false;
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
