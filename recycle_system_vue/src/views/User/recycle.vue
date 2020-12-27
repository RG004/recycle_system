<template>
  <el-container direction="vertical">
    <!--      <el-header style="height: 1px"></el-header>-->
    <div v-if="active==1">

      <div class="menu-wrapper" ref="menuWrapper">
        <el-menu

          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          style="width: 100%;"
        >
          <el-menu-item style="width: 14%" v-for="(item,index) in orderform.tableData"  @click="selectMenu(index)" :key="index">{{item.itemTypeName}}</el-menu-item>
          <!--          <el-menu-item index="1" style="width: 14%">塑料</el-menu-item>-->
          <!--          <el-menu-item index="2" style="width: 14%">钢铁</el-menu-item>-->
          <!--          <el-menu-item index="3" style="width: 15%">电子设备</el-menu-item>-->
          <!--          <el-menu-item index="4" style="width: 14%">废纸</el-menu-item>-->
          <!--          <el-menu-item index="5" style="width: 15%">有害垃圾</el-menu-item>-->
          <!--          <el-menu-item index="6" style="width: 14%">玻璃</el-menu-item>-->
          <!--          <el-menu-item  index="7"  style="width: 14%">其他</el-menu-item>-->
        </el-menu>
      </div>
      <!--      <div class="menu-wrapper" ref="menuWrapper">-->
      <!--        <ul>-->
      <!--          <li v-for="(item,index) in tableData" class="menu-item"  @click="selectMenu(index)" :key="index">-->
      <!--            <span class="text border-1px">{{item.kind}}</span>-->
      <!--          </li>-->
      <!--        </ul>-->
      <!--      </div>-->
      <div style="height: 380px;overflow: hidden;padding-left: 0px;"  class="foods-wrapper" ref="foodsWrapper">
        <ul style="list-style:none;">
          <li v-for="item in orderform.tableData" class="food-list food-list-hook" :key="item.itemTypeId">
            <h1 class="title">{{item.itemTypeName}}</h1>
            <ul v-for="good in item.itemsList" class="food-item " :key="good.itemId">
              <div class="content" style="float: left">
                <div class="name" >{{good.itemName}}</div>
                <div class="price" >单价：{{good.itemPrice}}（元/斤）</div>
                <div class="weight" ><el-input-number  v-model="good.weight"  :min="0" :precision="1" :step="0.5" ></el-input-number></div>
                <div>{{good.weight}}</div>
              </div>
            </ul>
          </li>
        </ul>
      </div>
    </div>

    <div class="block" v-if="active==2" style="margin: 0 auto ;height: 280px;padding-top: 100px;">
      <span class="demonstration">请选择上门时间</span>
      <div>{{this.orderform.scheduledTime}}</div>
      <el-date-picker
        v-model="orderform.scheduledTime"
        type="datetime"
        value-format="yyyy-MM-dd hh:mm:ss"
        placeholder="选择日期时间">
      </el-date-picker>
    </div>

    <div class="block2" v-if="active==3" style="width:900px; margin:  0 auto ;height: 280px;padding-top: 100px;">
      <span class="demonstration">请选择上门地址</span>
      <div>{{this.orderform.addressId}}</div>
      <el-table :data="addressList" >
        <el-table-column label="序号" width="200">
          <template slot-scope="scope">
            <span>{{scope.$index + 1}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="addressDetails" label="地址" width="500">
        </el-table-column>
        <el-table-column  fixed="right" label="操作">
          <template slot-scope="scope">
            <el-button  type="primary" round slot="reference" icon="el-icon-check" style="background-color: #B3C0D1;border-color: #B3C0D1"  @click="choose(scope.row.addressId)">选择</el-button>
          </template>
        </el-table-column>
      </el-table>

    </div>

    <el-steps :active="active" finish-status="success" style="float: left ;padding-top: 40px" >
      <el-step title="挑选物品"></el-step>
      <el-step title="预约时间"></el-step>
      <el-step title="选择地址"></el-step>
    </el-steps>
    <div style="float: left;">
      <el-button style="margin-top: 12px;"  @click="back" v-if="active!=1"> 上一步</el-button>

      <el-button style="margin-top: 12px;"  @click="next" v-if="active!=3"> 下一步</el-button>

      <el-button style="margin-top: 12px;"  @click="finish"  v-else> 完成</el-button>

    </div>





  </el-container>
</template>

<script>
  import BScroll from 'better-scroll'
  export default {
    name: 'recycle',
    data() {
      return {
        active:1 ,
        listHeight: [],
        scrollY: 0,
        addressList: [{
          addressId:1,
          addressDetails:'浙江省杭州市西湖区留和路288号浙江工业大学屏峰校区'
        }],
        orderform:{
          userId:1,
          addressId:1,
          scheduledTime:'',
          tableData:[
            {
              itemTypeId:1,
              itemTypeName:'塑料',
              itemsList:[
                {
                  itemId:1,
                  itemName:'塑料瓶',
                  itemPrice:'5',
                  weight:0
                },

                {
                  itemId:1,
                  itemName:'塑料瓶',
                  itemPrice:'5',
                  weight:0.5
                },
                {
                  itemId:1,
                  itemName:'塑料瓶',
                  itemPrice:'5',
                  weight:0.5
                },
                {
                  itemId:1,
                  itemName:'塑料瓶',
                  itemPrice:'5',
                  weight:0.5
                },
                {
                  itemId:1,
                  itemName:'塑料瓶',
                  itemPrice:'5',
                  weight:0.5
                },
                {
                  itemId:1,
                  itemName:'塑料瓶',
                  itemPrice:'5',
                  weight:0.5
                },
                {
                  itemId:1,
                  itemName:'塑料瓶',
                  itemPrice:'5',
                  weight:0.5
                },
                {
                  itemId:1,
                  itemName:'1451651651',
                  itemPrice:'5',
                  weight:0.5
                },
              ],
            }
          ]
        },

      }
    },
    computed:{
      // currentIndex () {
      //   //第一种写法 有点繁琐 不推荐
      //   //   for (let i = 0; i < this.listHeight.length; i++) {
      //   //     let height1 = this.listHeight[i]
      //   //     let height2 = this.listHeight[i + 1]
      //   //     if (!height2 || (this.scrollY >= height1 && this.scrollY < height2)) {
      //   //       return i
      //   //     }
      //   //   }
      //   //   return 0
      //   //  解构赋值
      //
      //   // 第二种写法已改进   根据条件计算 产生一个结果 findIndex查找满足条件的下标 没找到返回-1
      //   const index = this.listHeight.findIndex((item, index) => {
      //     //   如果滚动的距离>=左侧列表当前的高度并且 小于下一项的高度
      //     return this.scrollY >= item && this.scrollY < this.listHeight[index + 1]
      //   })
      //
      //   return index
      // }
    },
    created(){
      const _this=this;
      axios.get('http://localhost:8181/getallitem').then(function (resp) {
          console.log(resp);
          // for(var i=0,len=resp.data.length;i<=len;i++){
          //
          //   _this.orderform.tableData[i].itemTypeId=resp.data[i].itemTypeId;
          //   _this.orderform.tableData[i].itemName=resp.data[i].itemName;
          //   alert(_this.orderform.tableData[i].itemTypeId)
          //   // for(var j=0,l=resp.data[i].itemsList.length;j<l;j++){
          //   //   _this.orderform.tableData[i].itemsList[j].itemId=resp.data[i].itemsList[j].itemId;
          //   //   _this.orderform.tableData[i].itemsList[j].itemName=resp.data[i].itemsList[j].itemName;
          //   //   _this.orderform.tableData[i].itemsList[j].itemPrice=resp.data[i].itemsList[j].itemPrice;
          //   //   _this.orderform.tableData[i].itemsList[j].weight=0
          //   // }
          //
          // }


           _this.orderform.tableData=resp.data;
           // _this.tableData.items;List.weight=0;
          // _this.tableData.goods.goodid=resp.data.list.itemsList.itemId;
          // _this.tableData.goods.name=resp.data.list.itemsList.itemName;
          // _this.tableData.goods.price=resp.data.list.itemsList.itemPrice;
      })
      // for(var j=0,len=this.tableData.length;j<len;j++){
      //   for(var t=0,h=this.tableData[j].itemsList.length;t<h;t++){
      //     this.tableData[j].itemsList[t].weight=0
      //   }
      // }
      axios.get('http://localhost:8181/userAlladdress/'+this.$store.getters.getUserId+'').then(function (resp) {
        console.log(resp)
        _this.addressList=resp.data.addressList
      })

      // alert(this.tableData[0].itemsList[0].weight)
    },
    mounted(){
      //   这里用到一个定时器 等待数据渲染完毕 执行该方法 不然不能滑动 如果是真实数据 这两个方法放在this.$nextTick()里面
      //因为没有真实数据 只能用本地数据模拟下
      setTimeout(() => {
        this._initScroll()
        this._calculateHeight()
      }, 2)
    },
    methods: {
      //     设置两边列表可以滚动  内容区要比外层容器要高  列表先渲染 才可以滑动
      _initScroll () {

        // this.menuScroll = new BScroll(this.$refs.menuWrapper, {
        //   click: true
        // })
        this.foodsScroll = new BScroll(this.$refs.foodsWrapper, {
          click: true,
          probeType: 3,
          scrollY: true,
        })
        //    右侧表绑定滑动监听
        this.foodsScroll.on('scroll', (pos) => {
          //  获取每次滚动的值 绝对值
          this.scrollY = Math.abs(Math.round(pos.y))
        })
        //右侧列表绑定滑动结束监听
        this.foodsScroll.on('scrollEnd', (pos) => {
          this.scrollY = Math.abs(Math.round(pos.y))
          console.log(this.scrollY)
        })
      },
      _calculateHeight () {
        // 获取 所有左侧列表li  class 为 food-list-hook ul设置ref
        let foodList = this.$refs.foodsWrapper.getElementsByClassName('food-list-hook')

        let height=0;
        this.listHeight.push(height)
        // 第一种写法 有点繁琐 不推荐
        //  定义初始值 li可见高度为 0
        //   let height = 0
        // //   初始值0 添加到listHeight数组
        //   this.listHeight.push(height)
        // //   循环遍历所有左侧列表 得到所有li可视区域高度
        //   for (let i = 0; i < foodList.length; i++) {
        //     let item = foodList[i]
        //         // 高度+= 左侧列表当前项的可视高度
        //     height += item.clientHeight
        //     //  收集左侧列表所有li可见区域高度
        //     this.listHeight.push(height)
        //   }
        for (let i = 0; i < foodList.length; i++) {
          let item = foodList[i];
          height += item.clientHeight;
          this.listHeight.push(height);
        }

        // 第二种写法以改进  伪数组转为真数组 并循环遍历
        // Array.prototype.slice.call(foodList).forEach((i) =>{
        //   height += i.clientHeight
        //   this.listHeight.push(height)
        // })

      },
      //  点击左侧列表 右侧列表滑动到指定位置
      selectMenu (index) {

        let foodList = this.$refs.foodsWrapper.getElementsByClassName('food-list-hook')
        //   scrollToElement可以滑动到指定位置
        this.foodsScroll.scrollToElement(foodList[index],300)
        //    第一个参数对应的食品列表
        //  第二个参数为滑动 动画时长
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      next() {
        if (this.active++ > 2) this.active = 1;
      },
      back(){
        setTimeout(() => {
          this._initScroll()
          this._calculateHeight()
        }, 2)
        if(this.active!=1)this.active--;
      },
      choose(addressID){
        this.orderform.userId=this.$store.getters.getUserId;
        this.orderform.addressId=addressID;
      },
      finish(){
        axios.post('http://localhost:8181/placeanorder',this.orderform).then(function (resp) {
          console.log(resp)
        })
        active=1;
        this.$router.push({
          path:'/userdoinginorder'
        })

      }
    }
  }
</script>
<style   scoped>
  .foods-wrapper .title{
    border-left: 2px solid #d9dde1;
    font-size: 12px;
    width: 100%;
    height: 26px;
    line-height: 26px;
    padding-left: 10px;
    margin-left: 12PX;
    background:#f0f0f0;
    color: rgb(147,153,159);

  }
  .foods-wrapper .food-item{
    display: flex;
    margin: 18px;
    padding-bottom: 18px;
    border:rgba(7,17,27,.1) 1PX solid;
  }
  .food-item .content{
    flex: 1;
  }
  .food-item .name{
    /*display: inline-block;*/
    margin: auto 0;
    float: left;
    line-height: 14px;
    font-size: 14px;
    color: rgb(7,17,27);

  }
  .price{
    /*display: inline-block;*/
    float: left;
    line-height: 14px;
    font-size: 14px;
    color: rgb(240,20,20);
    font-weight: 700;

  }
  .weight{
    margin-top: 20px;
    vertical-align: middle;
    display: inline-block;
    float: right;
  }
</style>


