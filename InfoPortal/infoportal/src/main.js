import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import message from './common/prompt'

Vue.config.productionTip = false
Vue.prototype.$message=message

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
