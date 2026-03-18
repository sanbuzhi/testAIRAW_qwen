```vue
<template>
  <el-container>
    <el-header>
      <h1>订单列表</h1>
    </el-header>
    <el-main>
      <el-table :data="orders" style="width: 100%">
        <el-table-column prop="id" label="订单ID" width="180"></el-table-column>
        <el-table-column prop="customerName" label="客户姓名" width="180"></el-table-column>
        <el-table-column prop="orderDate" label="下单日期" width="180">
          <template #default="scope">
            {{ formatDateTime(scope.row.orderDate) }}
          </template>
        </el-table-column>
        <el-table-column prop="totalAmount" label="总金额" width="180"></el-table-column>
        <el-table-column prop="status" label="状态" width="180">
          <template #default="scope">
            <el-tag :type="getStatusType(scope.row.status)">
              {{ scope.row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button type="text" size="small" @click="handleViewOrder(scope.row)">查看</el-button>
            <el-button type="text" size="small" @click="handleEditOrder(scope.row)">编辑</el-button>
            <el-button type="text" size="small" @click="handleDeleteOrder(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        v-model:currentPage="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[10, 20, 30, 50]"
        :small="true"
        :layout="prev, pager, next, jumper, sizes, total"
        :total="totalOrders"
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

const orders = ref([]);
const currentPage = ref(1);
const pageSize = ref(10);
const totalOrders = ref(0);

const getStatusType = (status) => {
  switch (status) {
    case '待支付':
      return 'warning';
    case '已支付':
      return 'success';
    case '已取消':
      return 'danger';
    default:
      return '';
  }
};

const formatDateTime = (date) => {
  const d = new Date(date);
  return d.toLocaleString();
};

const fetchOrders = async () => {
  try {
    const response = await axios.get('/api/orders', {
      params: {
        page: currentPage.value,
        size: pageSize.value,
      },
    });
    orders.value = response.data.content;
    totalOrders.value = response.data.totalElements;
  } catch (error) {
    ElMessage.error('获取订单列表失败');
  }
};

const handleSizeChange = (newSize) => {
  pageSize.value = newSize;
  fetchOrders();
};

const handleCurrentChange = (newPage) => {
  currentPage.value = newPage;
  fetchOrders();
};

const handleViewOrder = (order) => {
  // 查看订单详情
  console.log('查看订单:', order);
};

const handleEditOrder = (order) => {
  // 编辑订单
  console.log('编辑订单:', order);
};

const handleDeleteOrder = async (orderId) => {
  try {
    await axios.delete(`/api/orders/${orderId}`);
    ElMessage.success('删除成功');
    fetchOrders();
  } catch (error) {
    ElMessage.error('删除失败');
  }
};

onMounted(() => {
  fetchOrders();
});
</script>

<style scoped>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-main {
  padding: 20px;
}
</style>
```