// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import './plugins/axios.js'
import './plugins/element.js'
import  VueParticles  from   'vue-particles'

Vue.config.productionTip = false
Vue.use(VueParticles)

new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
})
