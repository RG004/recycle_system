<template>
  <div>
    <div ref="chart" style="width: 700px;height: 300px"></div>
    <div ref="chart1" style="width: 700px;height: 300px"></div>
    <div ref="chart2" style="width: 700px;height: 400px"></div>
    <div ref="chart3" style="width: 600px;height: 300px"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import Vue from 'vue'
export default {
  methods: {
    initChart (container, option) {
      const myChart = echarts.init(container)
      myChart.setOption(option)
    }
  },
  mounted () {
    // 基于准备好的dom，初始化echarts实例
    let option = {
      title: {
        text: '根据下单月份统计订单数'
      },
      tooltip: {}, // 悬浮提示，不写就没有提示
      xAxis: { // 横坐标
        // data: JSON.stringify(this.y)
        type: 'category',
        data: vm.xData
      },
      yAxis: {
        type: 'value'
      }, // 纵坐标，可以不写根据data里的数据列出来
      series: [{
        name: '数量', // 可以不写
        type: 'bar', // 柱形
        data: vm.yData,
        color:'#48b7ae'
      }]
    }
    // 绘制图表
    this.initChart(this.$refs.chart, option)
    let option1 = {
      title: {
        text: '根据下单时间统计订单数'
      },
      tooltip: {},
      xAxis: {
        type: 'category',
        data: vm.xData1
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: vm.yData1,
        type: 'line',
        color:'#48b7ae'
      }]
    }
    // 绘制图表
    this.initChart(this.$refs.chart1, option1)
    let option2 = {
      title: {
        text: '每月回收金额'
      },
      tooltip: {},
      xAxis: {
        type: 'category',
        data: vm.xData2
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: vm.yData2,
        type: 'line',
        color:'#48b7ae'
      }]
    }
    // 绘制图表
    this.initChart(this.$refs.chart2, option2)
    let option3 = {
      title: {
        text: '废品统计',
        left: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['铝', '铁', '电池', '不锈钢','消毒剂']
      },
      series: [
        {
          name: '访问来源',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: vm.Data,
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    this.initChart(this.$refs.chart3, option3)

  },
  created () {
    const _this = this
    // eslint-disable-next-line no-undef
    axios.get('http://localhost:8181/adminselectCountbydate').then(function (resp) {
        console.log(resp)
        for (var j = 0, len = resp.data.length; j < len; j++) {
          // alert(_this.x[j].recycleOrderId)
          vm.yData.splice(j, 1, resp.data[j].count)
          vm.xData.splice(j, 1, resp.data[j].scheduledTime)
        }
      }
    )
    axios.get('http://localhost:8181/adminselectCountbytime').then(function (resp) {
        console.log(resp)
        for (var j = 0, len = resp.data.length; j < len; j++) {
          // alert(_this.x[j].recycleOrderId)
          vm.yData1.splice(j, 1, resp.data[j].count)
          vm.splice(j, 1, resp.data[j].scheduledTime+":00")
        }
      }
    )
    axios.get('http://localhost:8181/adminselectCountByitemid').then(function (resp) {
        console.log(resp)
        /*   Data=resp.data;
           alert(JSON.stringify(Data))*/
        for (var j = 0, len = resp.data.length; j < len; j++) {
          vm.Data.splice(j, 1, resp.data[j])
        }
      }
    )
    axios.get('http://localhost:8181/adminselectAmountbydate').then(function (resp) {
        console.log(resp)
        /*   Data=resp.data;
           alert(JSON.stringify(Data))*/
        for (var j = 0, len = resp.data.length; j < len; j++) {
          vm.yData2.splice(j, 1, resp.data[j].count)
          vm.xData2.splice(j, 1, resp.data[j].scheduledTime)
        }
      }
    )
  },
  data () {
    return {
      /* tableData: [{
        scheduledTime: '2018-9-19',
        recycleOrderId: 1
      },
      {
        scheduledTime: '2013-9-19',
        recycleOrderId: 2
      }]*/
      // x: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子'],
      // y: [1, 2, 3, 4, 5, 6],
    }
  }
}
// eslint-disable-next-line no-unused-vars
var vm = new Vue({
  data: {
    xData : ['2020-09-19', '2020-08-19', '2021-01-02', '2020-04-12'],
    yData : [1, 2, 1, 2],
    xData1: ['8:00', '9:00', '12:00', '13:00'],
    yData1 : [1, 2, 1, 2],
    xData2:['2020-9', '2020-10'],
    yData2 : [34,39],
    Data:[{value: 335, name: '铝'},
      {value: 310, name: '铁'},
      {value: 234, name: '电池'},
      {value: 135, name: '消毒剂'}]
  }
})

</script>

<style scoped>

</style>
