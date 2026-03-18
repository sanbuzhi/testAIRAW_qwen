```javascript
// frontend/src/api/request.js

import axios from 'axios';
import { ElMessage, ElMessageBox } from 'element-plus';
import { useUserStore } from '@/stores/user';

const request = axios.create({
  baseURL: process.env.VUE_APP_API_BASE_URL,
  timeout: 5000,
});

// 请求拦截器
request.interceptors.request.use(
  (config) => {
    const userStore = useUserStore();
    if (userStore.token) {
      config.headers.Authorization = `Bearer ${userStore.token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 响应拦截器
request.interceptors.response.use(
  (response) => {
    const { data } = response;
    if (data.code === 200) {
      return data.data;
    } else {
      ElMessage.error(data.message || '请求失败');
      return Promise.reject(data.message || '请求失败');
    }
  },
  (error) => {
    if (error.response) {
      const { status } = error.response;
      if (status === 401) {
        ElMessageBox.confirm('登录已过期，请重新登录', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }).then(() => {
          const userStore = useUserStore();
          userStore.logout();
          window.location.reload();
        });
      } else {
        ElMessage.error(error.response.data.message || '请求失败');
      }
    } else {
      ElMessage.error('网络连接异常，请稍后再试');
    }
    return Promise.reject(error);
  }
);

export default request;
```