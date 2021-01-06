<template>
  <el-container style="height:750px; border: 1px solid #eee">
    <el-aside width="220px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-active="this.$route.path" router>
        <el-menu-item index="/userhomepage">
          <i class="el-icon-s-home"></i>
          <span  slot="title">主页</span>
        </el-menu-item>
        <el-submenu index="3">
          <template slot="title">查看订单</template>
          <el-menu-item-group>
            <el-menu-item index="/adminallinorder" >查看历史订单</el-menu-item>
            <el-menu-item index="/admindoinginorder" >进行中订单</el-menu-item>
            <el-menu-item index="/adminalldonate" >查看历史捐赠</el-menu-item>
            <el-menu-item index="/admindoingdonate" >进行中捐赠</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title">管理信息</template>
          <el-menu-item-group>
            <el-menu-item index="/adminCollectorManage">管理派送员</el-menu-item>
            <el-menu-item index="/ItemTypeManage">管理物品种类</el-menu-item>
            <el-menu-item index="/ItemManage">管理物品</el-menu-item>
            <el-menu-item index="/HelpThePoorManager">管理资助地区</el-menu-item>
            <el-menu-item index="/adminecharts">数据统计</el-menu-item>
            <el-menu-item index="/login" @click="d">退出登录</el-menu-item>
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
            <template slot="title"><i class="el-icon-user" style="font-size: 25px ;"></i>{{this.$store.getters.getAdminName}}</template>
            <el-menu-item index="/userallinorder">我的订单</el-menu-item>
            <el-menu-item index="/useralterinfo">修改个人信息</el-menu-item>
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
        this.$store.commit('setAdminId',null)
        this.$store.commit('setAdminName',null)
        sessionStorage.removeItem('token')
        sessionStorage.removeItem('identity')
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
