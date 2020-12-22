import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userId:'',
    userName:''
  },
  mutations: {
    setUserId(state,userId){
      state.userId = userId
    },
    setUserName(state,userName){
      state.userName = userName
    },
  },
  getters:{
    getUserId:state => state.userId,
    getUserName:state => state.userName
  },
  actions: {
  },
  modules: {
  },
})
