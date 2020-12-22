<template>
    <el-container direction="vertical">
        <el-menu
          :default-active="1"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          style="width: 100%;"
          >
          <el-menu-item index="1" style="width: 14%">塑料</el-menu-item>
          <el-menu-item index="2" style="width: 14%">钢铁</el-menu-item>
          <el-menu-item index="3" style="width: 15%">电子设备</el-menu-item>
          <el-menu-item index="4" style="width: 14%">废纸</el-menu-item>
          <el-menu-item index="5" style="width: 15%">有害垃圾</el-menu-item>
          <el-menu-item index="6" style="width: 14%">玻璃</el-menu-item>
          <el-menu-item  index="7"  style="width: 14%">其他</el-menu-item>
        </el-menu>
      <div style="height: auto" v-if="active==1">
        <el-table :data="tableData" style=" width: 100%; "
                  height="380" >
          <el-table-column label=" 物品" prop="name" width="180" >
            <template slot-scope="scope">
              <span >{{scope.row.name}}</span>
            </template>
          </el-table-column>
          <el-table-column label=" 单价(元/斤)">
            <template slot-scope="scope">
              <span >{{scope.row.price}}</span>
            </template>
          </el-table-column>
          <el-table-column label="大约重量（斤）" fixed="right" width="300">
            <template slot-scope="scope">
              <el-input-number  v-model="scope.row.weight" :min="0" :precision="1" :step="0.5" ></el-input-number>
              {{scope.row.weight}}
            </template>
          </el-table-column>
        </el-table>
      </div>

      <div class="block" v-if="active==2" style="margin: 0 auto ;height: 280px;padding-top: 100px;">
        <span class="demonstration">请选择上门时间</span>
        <el-date-picker
          v-model="booktime"
          type="datetime"
          placeholder="选择日期时间">
        </el-date-picker>
      </div>
      <div class="block2" v-if="active==3" style="margin:  0 auto ;height: 280px;padding-top: 100px;">
        <i class="el-icon-success" style="color:greenyellow; font-size:52px ;float:left"></i>
        <div>
          <span style="float:left;padding-top: 10px">您已成功下单</span>
        </div>

      </div>

        <el-steps :active="active" finish-status="success" style="float: left ;padding-top: 40px" >
          <el-step title="挑选物品"></el-step>
          <el-step title="预约时间"></el-step>
          <el-step title="步骤 3"></el-step>
        </el-steps>
        <div style="float: left;">
          <el-button style="margin-top: 12px;"  @click="next" > 下一步</el-button>
        </div>

    </el-container>
</template>

<script>
  export default {
    name: 'recycle',
    data() {
      return {
        active:1,
        booktime:'',
        tableData:[
          {
            kind:1,
            name:'塑料瓶',
            price:'5',
            weight:0.5
          },{
            kind:1,
            name:'塑料泡沫',
            price:'5',
            weight:0.5
          },{
            kind:1,
            name:'塑料薄膜',
            price:'5',
            weight:0.5
          },{
            kind:2,
            name:'铁',
            price:'100',
            weight:0.5
          },{
            kind:2,
            name:'铝',
            price:'100',
            weight:0.5
          },{
            kind:3,
            name:'手机',
            price:'100',
            weight:0.5
          },{
            kind:3,
            name:'电脑',
            price:'100',
            weight:0.5
          },{
            kind:3,
            name:'电视',
            price:'200',
            weight:0
          },{
            kind:3,
            name:'空调',
            price:'300',
            weight:0
          },{
            kind:3,
            name:'洗衣机',
            price:'150',
            weight:0
          },{
            kind:4,
            name:'包装纸',
            price:'150',
            weight:0
          },{
            kind:4,
            name:'电池',
            price:'150',
            weight:0
          },{
            kind:5,
            name:'玻璃瓶',
            price:'150',
            weight:0
          }
        ]
      }
    },
    methods: {

      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      next() {
        if (this.active++ > 2) this.active = 1
      }
    }
  }
</script>


