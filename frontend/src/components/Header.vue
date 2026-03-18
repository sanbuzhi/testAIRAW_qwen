```vue
<template>
  <el-header class="header">
    <div class="logo">
      <img src="@/assets/logo.png" alt="Logo" />
      <span>美妆管理系统</span>
    </div>
    <el-menu
      :default-active="activeIndex"
      class="menu"
      mode="horizontal"
      @select="handleSelect"
    >
      <el-menu-item index="1">首页</el-menu-item>
      <el-menu-item index="2">产品管理</el-menu-item>
      <el-menu-item index="3">订单管理</el-menu-item>
      <el-menu-item index="4">用户管理</el-menu-item>
      <el-menu-item index="5">设置</el-menu-item>
    </el-menu>
    <div class="user-info">
      <el-dropdown>
        <span class="el-dropdown-link">
          {{ userName }}
          <el-icon class="el-icon--right"><caret-bottom /></el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="handleLogout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </el-header>
</template>

<script setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';

const router = useRouter();
const activeIndex = ref('1');
const userName = ref('张三'); // 假设用户名为张三，实际应用中应从后端获取

const handleSelect = (key, keyPath) => {
  switch (key) {
    case '1':
      router.push({ name: 'Home' });
      break;
    case '2':
      router.push({ name: 'ProductManagement' });
      break;
    case '3':
      router.push({ name: 'OrderManagement' });
      break;
    case '4':
      router.push({ name: 'UserManagement' });
      break;
    case '5':
      router.push({ name: 'Settings' });
      break;
    default:
      break;
  }
};

const handleLogout = () => {
  // 这里可以添加登出逻辑，例如清除token等
  ElMessage.success('已退出登录');
  router.push({ name: 'Login' });
};
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

.logo {
  display: flex;
  align-items: center;
}

.logo img {
  width: 40px;
  height: 40px;
  margin-right: 10px;
}

.menu {
  flex: 1;
  text-align: center;
}

.user-info {
  display: flex;
  align-items: center;
}

.el-dropdown-link {
  cursor: pointer;
  color: #606266;
}

.el-icon--right {
  margin-left: 5px;
}
</style>
```