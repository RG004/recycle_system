<template>
  <el-container style="height:750px; border: 1px solid #eee">
    <el-aside width="220px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-active="this.$route.path" router>
        <el-menu-item index="/homepage">
          <i class="el-icon-s-home"></i>
          <span  slot="title">主页</span>
        </el-menu-item>
        <el-submenu index="2">
          <template slot="title">操作</template>
          <el-menu-item-group>
            <el-menu-item index="/recycle" >资源回收</el-menu-item>
            <el-menu-item index="/recycle" >资源捐赠</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">查看订单</template>
          <el-menu-item-group>
            <el-menu-item index="/allinorder" >查看历史订单</el-menu-item>
            <el-menu-item index="/doinginorder" >进行中订单</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title">设置</template>
          <el-menu-item-group>
            <el-menu-item index="/alterinfo">修改个人信息</el-menu-item>
            <el-menu-item index="/login" @click="d">退出登录</el-menu-item>
            <el-menu-item index="/test" >测试</el-menu-item>
            <el-menu-item index="/maptest" >地图测试</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">

        <el-menu  class="el-menu-demo" mode="horizontal" @select="handleSelect"  router>
          <el-menu-item  style="width: 930px"></el-menu-item>
          <el-menu-item><i class="el-icon-edit" style="font-size: 25px;"></i></el-menu-item>
          <el-menu-item><i class="el-icon-share" style="font-size: 25px;"></i></el-menu-item>
          <el-menu-item><i class="el-icon-set-up" style="font-size: 25px;"></i></el-menu-item>
          <el-submenu index="4">
            <template slot="title"><i class="el-icon-user" style="font-size: 25px ;"></i>{{this.$store.getters.getUserName}}</template>
            <el-menu-item index="/allinorder">我的订单</el-menu-item>
            <el-menu-item index="/alterinfo">修改个人信息</el-menu-item>
            <el-menu-item index="/login" @click="d">退出登录</el-menu-item>


          </el-submenu>
        </el-menu>

      </el-header>
      <el-main>
        <router-view/>
      </el-main>

    </el-container>
  </el-container>


</template>
<script>
  export default {
    methods:{
      d(){
        this.$store.commit('setUserId',null)
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style scoped>
  .el-header {

    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>
