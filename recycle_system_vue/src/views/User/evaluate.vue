<template>
  <el-form  :model="form" ref="form" style="width: 500px" >
    <el-form-item  prop="evaluationScore">
      <h1 class="title" style="width: 50px;">分数</h1>
      <el-select v-model="form.evaluationScore" placeholder="请选择" style="width: 500px">
        <el-option v-for="item in options" :key="item.evaluationScore" :label="item.evaluationScore" :value="item.evaluationScore"></el-option>
      </el-select>
    </el-form-item>
    <h1 class="title" style="width: 80px;">评价内容</h1>
    <el-form-item  prop="evaluationDetails">
      <el-input v-model="form.evaluationDetails" :rows="5" type="textarea"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button  @click="finish()">提交</el-button>
      <el-button  @click="resetForm('form')">重置</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
  export default {
    data() {
      return {
        form:{
          donateId:5,
          evaluationId:'',
          recycleOrderId:'',
          evaluationDetails:'',
          evaluationScore:100,
        },
        options:[
          {
            evaluationScore:100,
          },
          {
            evaluationScore:80,
          },
          {
            evaluationScore:60,
          },
          {
            evaluationScore:40,
          },
          {
            evaluationScore:20,
          },
        ],
      }
    },
    created(){
      this.form.donateId=''
      this.form.recycleOrderId=''
      this.form.donateId=this.$route.query.donateId
      this.form.recycleOrderId=this.$route.query.recycleOrderId
    },
    methods: {
      resetForm(formName){
        this.$refs[formName].resetFields();
      },
      finish(){
        const _this=this
        if(this.form.donateId==null){
          axios.post('http://localhost:8181/evaluationorder',this.form).then(function (resp) {
            console.log(resp)
            if(resp.data){
              _this.$alert('评价填写成功','消息',{
                confirmButtonText:'确定',
                callback:action => {
                  _this.$router.go(-1)
                }
              });
            }
          })
        }else if(this.form.recycleOrderId==null){
          axios.post('http://localhost:8181/evaluationdonation',this.form).then(function (resp) {
            console.log(resp)
            if(resp.data){
              _this.$alert('评价填写成功','消息',{
                confirmButtonText:'确定',
                callback:action => {
                  _this.$router.go(-1)
                }
              });
            }
          })
        }
      }
    }
  }
</script>
<style   scoped>
  .title{
    border-left: 2px solid #d9dde1;
    font-size: 15px;
    font-weight: 700;
    height: 26px;
    line-height: 26px;
    padding-left: 10px;
    background:#f0f0f0;
    color: rgb(147,153,159);
  }
</style>


