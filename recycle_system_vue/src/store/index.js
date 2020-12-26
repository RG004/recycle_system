import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userId:'',
    userName:'',
    collectorId:'',
    collectorName:'',
  },
  mutations: {
    setUserId(state,userId){
      state.userId = userId
    },
    setUserName(state,userName){
      state.userName = userName
    },
    setCollectorId(state,collectorId){
      state.collectorId = collectorId
    },
    setCollectorName(state,collectorName){
      state.collectorName = collectorName
    },
  },
  getters:{
    getUserId:state => state.userId,
    getUserName:state => state.userName,
    getCollectorId:state => state.collectorId,
    getCollectorName:state => state.collectorName,
  },
  actions: {
  },
  modules: {
  },
})
