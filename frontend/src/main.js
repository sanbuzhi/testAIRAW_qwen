```javascript
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import * as Icons from '@element-plus/icons-vue';

const app = createApp(App);

// 注册Element Plus组件库
app.use(ElementPlus);

// 注册Element Plus图标
Object.keys(Icons).forEach(key => {
  app.component(key, Icons[key]);
});

// 注册路由
app.use(router);

// 注册Vuex状态管理
app.use(store);

// 挂载应用
app.mount('#app');
```