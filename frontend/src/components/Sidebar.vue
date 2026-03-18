```vue
<template>
  <el-aside :width="isCollapse ? '64px' : '200px'" class="sidebar">
    <div class="logo" @click="toggleCollapse">
      <img src="@/assets/logo.png" alt="Logo" />
    </div>
    <el-menu
      :default-active="activeIndex"
      :collapse="isCollapse"
      :router="true"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      unique-opened
    >
      <el-sub-menu index="1">
        <template #title>
          <el-icon><location /></el-icon>
          <span>产品管理</span>
        </template>
        <el-menu-item index="/products">产品列表</el-menu-item>
        <el-menu-item index="/add-product">添加产品</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="2">
        <template #title>
          <el-icon><document /></el-icon>
          <span>订单管理</span>
        </template>
        <el-menu-item index="/orders">订单列表</el-menu-item>
        <el-menu-item index="/add-order">添加订单</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="3">
        <template #title>
          <el-icon><setting /></el-icon>
          <span>系统设置</span>
        </template>
        <el-menu-item index="/settings">设置</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </el-aside>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const isCollapse = ref(false);

const activeIndex = computed(() => {
  return route.path;
});

const toggleCollapse = () => {
  isCollapse.value = !isCollapse.value;
};
</script>

<style scoped>
.sidebar {
  height: 100vh;
  border-right: 1px solid #d3dce6;
}

.logo {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 60px;
  cursor: pointer;
}

.logo img {
  width: 80%;
  max-width: 150px;
}
</style>
```