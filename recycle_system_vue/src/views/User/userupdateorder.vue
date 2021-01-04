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
        </el-menu>
      </div>
      <div style="height: 380px;overflow: hidden;padding-left: 0px;"  class="foods-wrapper" ref="foodsWrapper">
        <ul style="list-style:none;">
          <li v-for="item in orderform.tableData" class="food-list food-list-hook" :key="item.itemTypeId">
            <h1 class="title">{{item.itemTypeName}}</h1>
            <ul v-for="good in item.itemsList" class="food-item " :key="good.itemId">
              <div class="content" style="float: left">
                <div class="name" >{{good.itemName}}</div>
                <div class="price" >单价：{{good.itemPrice}}（元/斤）</div>
                <div class="weight" ><el-input-number  v-model="good.quantity"  :min="0" :precision="1" :step="0.5" ></el-input-number></div>
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
      <el-button style="margin-top: 12px;"  @click="next1" v-if="active==1"> 下一步</el-button>
      <el-button style="margin-top: 12px;"  @click="next2" v-if="active==2"> 下一步</el-button>
      <el-button style="margin-top: 12px;"  @click="finish"  v-if="active==3"> 确认修改</el-button>
    </div>
  </el-container>
</template>
<script>
  import BScroll from 'better-scroll'
  export default {
    data() {
      return {
        maxWeight:0,
        active:1 ,
        listHeight: [],
        scrollY: 0,
        addressList: [{
          addressId:1,
          addressDetails:'浙江省杭州市西湖区留和路288号浙江工业大学屏峰校区'
        }],
        orderform:{
          recycleOrderId:1,
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
                  quantity:0.5
                },
                {
                  itemId:1,
                  itemName:'塑料瓶',
                  itemPrice:'5',
                  quantity:0.5
                },
                {
                  itemId:1,
                  itemName:'塑料瓶',
                  itemPrice:'5',
                  quantity:0.5
                },
                {
                  itemId:1,
                  itemName:'1451651651',
                  itemPrice:'5',
                  quantity:0.5
                },
                {
                  itemId:1,
                  itemName:'1451651651',
                  itemPrice:'5',
                  quantity:0.5
                },
              ],
            }
          ]
        },

      }
    },
    created(){
      const _this=this;
      axios.get('http://localhost:8181/getallitem').then(function (resp) {
        // console.log(resp);
        _this.orderform.tableData=resp.data;
        _this.$nextTick(() => {
          _this._initScroll()
          _this._calculateHeight()
        });
        console.log(_this.orderform.tableData.length)
        console.log(resp.data.length)

      })
      axios.get('http://localhost:8181/userAlladdress/'+this.$store.getters.getUserId+'').then(function (resp) {
        // console.log(resp)
        _this.addressList=resp.data.addressList
      })
      _this.orderform.recycleOrderId=_this.$route.query.recycleOrderId
      axios.get('http://localhost:8181/OrdersDetail/'+_this.orderform.recycleOrderId+'').then(function (resp2) {
        console.log(resp2)
        for(var i=0,len=resp2.data.length;i<len;i++){
          var itemName=resp2.data[i].itemName
          for(var j=0,len2=_this.orderform.tableData.length;j<len2;j++){
            for(var k=0,len3=_this.orderform.tableData[j].itemsList.length;k<len3;k++){
              if(_this.orderform.tableData[j].itemsList[k].itemName==itemName){
                _this.orderform.tableData[j].itemsList[k].quantity=resp2.data[i].quantity
              }
            }
          }
        }
      })
      axios.get('http://localhost:8181/getAnOrder/'+_this.orderform.recycleOrderId+'').then(function (resp) {
        console.log(resp)
        _this.orderform.scheduledTime=resp.data.scheduledTime
      })

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
        for (let i = 0; i < foodList.length; i++) {
          let item = foodList[i];
          height += item.clientHeight;
          this.listHeight.push(height);
        }
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
      next1() {
        for(var i=0,len=this.orderform.tableData.length;i<len;i++){
          for(var j=0,len2=this.orderform.tableData[i].itemsList.length;j<len2;j++){
            if(this.orderform.tableData[i].itemsList[j].quantity>this.maxWeight){
              this.maxWeight=this.orderform.tableData[i].itemsList[j].quantity
            }
          }
        }
        if(this.maxWeight==0){
          alert('请选择货物')
        }
        else this.active = 2;
      },
      next2() {

        if(this.orderform.scheduledTime==''){
          alert('请选择预约时间')
        }
        else this.active = 3;
      },
      back(){
        if(this.active==2){
          this.maxWeight=0;
        }
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
        axios.post('http://localhost:8181/updateanorder',this.orderform).then(function (resp) {
          console.log(resp)
        })
        this.active=1;
        this.$router.push({
          path:'/userdoinginorder'
        })

      }
    }
  }
</script>
<style   scoped>
  .menu-wrapper{
    height: 58px;
    overflow: hidden;
  }
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


