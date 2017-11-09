// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import lamboUi from 'lambo-ui';
import iView from 'iview';
import App from './App';
import router from './router';
import 'iview/dist/styles/iview.css';

Vue.use(iView);
Vue.use(lamboUi);

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})

router.replace('login')
