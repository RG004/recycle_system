<template>
  <el-container direction="vertical" style="width: 500px">
    <div>
      <div>请填写评价分数：</div>
      <el-input   v-model="form.evaluationScore"></el-input>
      <div>请填写评价：</div>
      <el-input type="textarea" :rows="5" v-model="form.evaluationDetails"></el-input>
    </div>
    <div style="float: left;margin-top: 50px;">
      <el-button style="margin-top: 12px;"  @click="finish" > 提交评价</el-button>
    </div>
  </el-container>
</template>
<script>
  export default {
    data() {
      return {
        form:{
          donateId:1,
          evaluationId:1,
          evaluationDetails:'',
          evaluationScore:100,
        }
      }
    },
    created(){
      const _this=this
      _this.form.donateId=_this.$route.query.donateId
    },
    methods: {
      finish(){
        axios.post('http://localhost:8181/evaluationdonation',this.form).then(function (resp) {
          console.log(resp)
        })
        this.$router.push({
          path:'/useralldonate'
        })
      }
    }
  }
</script>
<style   scoped>
</style>


