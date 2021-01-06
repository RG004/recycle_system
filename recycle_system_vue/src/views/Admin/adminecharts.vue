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
// eslint-disable-next-line no-unused-vars
var xData = ['2020-9', '2020-10']
var yData = [1, 2,]
var xData1= ['8:00', '9:00', '12:00', '13:00']
var yData1 = [1, 2, 1, 2]
var xData2= ['2020-9', '2020-10']
var yData2 = [34,39]
var Data=[{value: 335, name: '铝'},
  {value: 310, name: '铁'},
  {value: 234, name: '电池'},
  {value: 135, name: '消毒剂'}]
// eslint-disable-next-line no-unused-vars,no-undef
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
        data: xData
      },
      yAxis: {
        type: 'value'
      }, // 纵坐标，可以不写根据data里的数据列出来
      series: [{
        name: '数量', // 可以不写
        type: 'bar', // 柱形
        data: yData
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
        data: xData1
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: yData1,
        type: 'line'
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
        data: xData2
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: yData2,
        type: 'line'
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
        data: ['铝', '铁', '电池', '消毒剂']
      },
      series: [
        {
          name: '访问来源',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: Data,
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
    /* let option2 = {
      tooltip: {},
      series: [{
        data: [{
          name: 'Apples',
          value: 70
        }, {
          name: 'Strawberries',
          value: 68
        }, {
          name: 'Bananas',
          value: 48
        }, {
          name: 'Oranges',
          value: 40
        }, {
          name: 'Pears',
          value: 32
        }, {
          name: 'Pineapples',
          value: 27
        }, {
          name: 'Grapes',
          value: 18
        }],
        type: 'pie'
      }]
    }
    this.initChart(this.$refs.chart2, option2)
    var data = [
      [[28604, 77, 17096869, 'Australia', 1990], [31163, 77.4, 27662440, 'Canada', 1990], [1516, 68, 1154605773, 'China', 1990], [13670, 74.7, 10582082, 'Cuba', 1990], [28599, 75, 4986705, 'Finland', 1990], [29476, 77.1, 56943299, 'France', 1990], [31476, 75.4, 78958237, 'Germany', 1990], [28666, 78.1, 254830, 'Iceland', 1990], [1777, 57.7, 870601776, 'India', 1990], [29550, 79.1, 122249285, 'Japan', 1990], [2076, 67.9, 20194354, 'North Korea', 1990], [12087, 72, 42972254, 'South Korea', 1990], [24021, 75.4, 3397534, 'New Zealand', 1990], [43296, 76.8, 4240375, 'Norway', 1990], [10088, 70.8, 38195258, 'Poland', 1990], [19349, 69.6, 147568552, 'Russia', 1990], [10670, 67.3, 53994605, 'Turkey', 1990], [26424, 75.7, 57110117, 'United Kingdom', 1990], [37062, 75.4, 252847810, 'United States', 1990]],
      [[44056, 81.8, 23968973, 'Australia', 2015], [43294, 81.7, 35939927, 'Canada', 2015], [13334, 76.9, 1376048943, 'China', 2015], [21291, 78.5, 11389562, 'Cuba', 2015], [38923, 80.8, 5503457, 'Finland', 2015], [37599, 81.9, 64395345, 'France', 2015], [44053, 81.1, 80688545, 'Germany', 2015], [42182, 82.8, 329425, 'Iceland', 2015], [5903, 66.8, 1311050527, 'India', 2015], [36162, 83.5, 126573481, 'Japan', 2015], [1390, 71.4, 25155317, 'North Korea', 2015], [34644, 80.7, 50293439, 'South Korea', 2015], [34186, 80.6, 4528526, 'New Zealand', 2015], [64304, 81.6, 5210967, 'Norway', 2015], [24787, 77.3, 38611794, 'Poland', 2015], [23038, 73.13, 143456918, 'Russia', 2015], [19360, 76.5, 78665830, 'Turkey', 2015], [38225, 81.4, 64715810, 'United Kingdom', 2015], [53354, 79.1, 321773631, 'United States', 2015]]
    ]
    let option3 = {
      xAxis: {
        splitLine: {
          lineStyle: {
            type: 'dashed'
          }
        }
      },
      yAxis: {
        splitLine: {
          lineStyle: {
            type: 'dashed'
          }
        },
        scale: true
      },
      series: [{
        name: '1990',
        data: data[0],
        type: 'scatter',
        symbolSize: function (data) {
          return Math.sqrt(data[2]) / 5e2
        },
        emphasis: {
          label: {
            show: true,
            formatter: function (param) {
              return param.data[3]
            },
            position: 'top'
          }
        },
        itemStyle: {
          shadowBlur: 10,
          shadowColor: 'rgba(120, 36, 50, 0.5)',
          shadowOffsetY: 5,
          color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
            offset: 0,
            color: 'rgb(251, 118, 123)'
          }, {
            offset: 1,
            color: 'rgb(204, 46, 72)'
          }])
        }
      }, {
        name: '2015',
        data: data[1],
        type: 'scatter',
        symbolSize: function (data) {
          return Math.sqrt(data[2]) / 5e2
        },
        emphasis: {
          label: {
            show: true,
            formatter: function (param) {
              return param.data[3]
            },
            position: 'top'
          }
        },
        itemStyle: {
          shadowBlur: 10,
          shadowColor: 'rgba(25, 100, 150, 0.5)',
          shadowOffsetY: 5,
          color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
            offset: 0,
            color: 'rgb(129, 227, 238)'
          }, {
            offset: 1,
            color: 'rgb(25, 183, 207)'
          }])
        }
      }]
    }
    this.initChart(this.$refs.chart3, option3) */
  },
  created () {
    const _this = this
    // eslint-disable-next-line no-undef
    axios.get('http://localhost:8181/adminselectCountbydate').then(function (resp) {
        console.log(resp)
        for (var j = 0, len = resp.data.length; j < len; j++) {
          // alert(_this.x[j].recycleOrderId)
          yData.splice(j, 1, resp.data[j].count)
          xData.splice(j, 1, resp.data[j].scheduledTime)
        }
      }
    )
    axios.get('http://localhost:8181/adminselectCountbytime').then(function (resp) {
        console.log(resp)
        for (var j = 0, len = resp.data.length; j < len; j++) {
          // alert(_this.x[j].recycleOrderId)
          yData1.splice(j, 1, resp.data[j].count)
          xData1.splice(j, 1, resp.data[j].scheduledTime+":00")
        }
      }
    )
    axios.get('http://localhost:8181/adminselectCountByitemid').then(function (resp) {
        console.log(resp)
     /*   Data=resp.data;
        alert(JSON.stringify(Data))*/
        for (var j = 0, len = resp.data.length; j < len; j++) {
          Data.splice(j, 1, resp.data[j])
        }
      }
    )
    axios.get('http://localhost:8181/adminselectAmountbydate').then(function (resp) {
        console.log(resp)
        /*   Data=resp.data;
           alert(JSON.stringify(Data))*/
        for (var j = 0, len = resp.data.length; j < len; j++) {
          yData2.splice(j, 1, resp.data[j].count)
          xData2.splice(j, 1, resp.data[j].scheduledTime)
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
      }],
      // x: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子'],
      // y: [1, 2, 3, 4, 5, 6],
      xData: ['2013-9-19', '2018-9-19', '2019-2-22', '2020-4-12'],
      /!* yData: [
        {recycleOrderId: 1
        },
        {
          recycleOrderId: 2
        },
        {recycleOrderId: 1
        },
        {
          recycleOrderId: 2
        }] *!/
      yData: [1, 2, 1, 2] */
    }
  }
}
</script>

<style scoped>

</style>
