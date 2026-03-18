```vue
<template>
  <el-container>
    <el-header>
      <h1>美妆管理系统</h1>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu :default-active="activeIndex" class="el-menu-vertical-demo" @select="handleSelect">
          <el-menu-item index="1" @click="goToPage('Home')">
            <i class="el-icon-s-home"></i>
            <span slot="title">首页</span>
          </el-menu-item>
          <el-menu-item index="2" @click="goToPage('Products')">
            <i class="el-icon-s-goods"></i>
            <span slot="title">产品管理</span>
          </el-menu-item>
          <el-menu-item index="3" @click="goToPage('Orders')">
            <i class="el-icon-s-order"></i>
            <span slot="title">订单管理</span>
          </el-menu-item>
          <el-menu-item index="4" @click="goToPage('Customers')">
            <i class="el-icon-user-solid"></i>
            <span slot="title">客户管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

export default {
  name: 'App',
  setup() {
    const activeIndex = ref('1');
    const router = useRouter();

    const handleSelect = (key, keyPath) => {
      activeIndex.value = key;
    };

    const goToPage = (page) => {
      router.push({ name: page });
    };

    return {
      activeIndex,
      handleSelect,
      goToPage,
    };
  },
};
</script>

<style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: left;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
```