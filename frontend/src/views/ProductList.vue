```vue
<template>
  <el-container>
    <el-header>
      <h1>美妆管理系统 - 产品列表</h1>
    </el-header>
    <el-main>
      <el-table :data="products" style="width: 100%">
        <el-table-column prop="id" label="ID" width="80"></el-table-column>
        <el-table-column prop="name" label="产品名称" width="180"></el-table-column>
        <el-table-column prop="brand" label="品牌" width="180"></el-table-column>
        <el-table-column prop="category" label="分类" width="180"></el-table-column>
        <el-table-column prop="price" label="价格" width="120"></el-table-column>
        <el-table-column prop="stock" label="库存" width="120"></el-table-column>
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button type="primary" size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        v-model:currentPage="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[10, 20, 30, 50]"
        :small="false"
        :disabled="false"
        :background="true"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </el-main>
  </el-container>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { ElMessage } from 'element-plus';
import axios from 'axios';

const products = ref([]);
const currentPage = ref(1);
const pageSize = ref(10);
const total = ref(0);

const fetchProducts = async () => {
  try {
    const response = await axios.get('/api/products', {
      params: {
        page: currentPage.value,
        size: pageSize.value
      }
    });
    products.value = response.data.content;
    total.value = response.data.totalElements;
  } catch (error) {
    ElMessage.error('获取产品列表失败');
  }
};

onMounted(() => {
  fetchProducts();
});

const handleSizeChange = (newSize) => {
  pageSize.value = newSize;
  fetchProducts();
};

const handleCurrentChange = (newPage) => {
  currentPage.value = newPage;
  fetchProducts();
};

const handleEdit = (product) => {
  ElMessage.info(`编辑产品: ${product.name}`);
  // 这里可以添加跳转到编辑页面的逻辑
};

const handleDelete = async (productId) => {
  try {
    await axios.delete(`/api/products/${productId}`);
    ElMessage.success('删除成功');
    fetchProducts();
  } catch (error) {
    ElMessage.error('删除失败');
  }
};
</script>

<style scoped>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-main {
  padding: 10px;
}
</style>
```