```vue
<template>
  <div class="home">
    <el-container>
      <el-header>
        <h1>美妆管理系统</h1>
      </el-header>
      <el-main>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-card class="box-card">
              <template #header>
                <div class="card-header">
                  <span>最新产品</span>
                </div>
              </template>
              <el-table :data="latestProducts" style="width: 100%">
                <el-table-column prop="name" label="产品名称" width="180"></el-table-column>
                <el-table-column prop="brand" label="品牌" width="180"></el-table-column>
                <el-table-column prop="price" label="价格" width="180"></el-table-column>
                <el-table-column prop="stock" label="库存" width="180"></el-table-column>
              </el-table>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card class="box-card">
              <template #header>
                <div class="card-header">
                  <span>热销产品</span>
                </div>
              </template>
              <el-table :data="hotProducts" style="width: 100%">
                <el-table-column prop="name" label="产品名称" width="180"></el-table-column>
                <el-table-column prop="brand" label="品牌" width="180"></el-table-column>
                <el-table-column prop="price" label="价格" width="180"></el-table-column>
                <el-table-column prop="sales" label="销量" width="180"></el-table-column>
              </el-table>
            </el-card>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { ElContainer, ElHeader, ElMain, ElRow, ElCol, ElCard, ElTable, ElTableColumn } from 'element-plus';

const latestProducts = ref([]);
const hotProducts = ref([]);

// 模拟从后端获取数据
const fetchLatestProducts = async () => {
  // 这里可以替换为实际的API请求
  const response = await new Promise((resolve) => {
    setTimeout(() => {
      resolve([
        { name: '口红A', brand: '品牌A', price: 150, stock: 100 },
        { name: '眼影B', brand: '品牌B', price: 200, stock: 50 },
        { name: '粉底C', brand: '品牌C', price: 300, stock: 75 }
      ]);
    }, 1000);
  });
  latestProducts.value = response;
};

const fetchHotProducts = async () => {
  // 这里可以替换为实际的API请求
  const response = await new Promise((resolve) => {
    setTimeout(() => {
      resolve([
        { name: '口红D', brand: '品牌D', price: 180, sales: 200 },
        { name: '眼影E', brand: '品牌E', price: 220, sales: 150 },
        { name: '粉底F', brand: '品牌F', price: 320, sales: 100 }
      ]);
    }, 1000);
  });
  hotProducts.value = response;
};

onMounted(() => {
  fetchLatestProducts();
  fetchHotProducts();
});
</script>

<style scoped>
.home {
  padding: 20px;
}

.el-header {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.box-card {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
```