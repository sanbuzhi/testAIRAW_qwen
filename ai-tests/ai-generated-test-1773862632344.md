# README.md

## 项目简介
美妆管理系统 (Beauty Management System) 是一个专为美妆行业设计的全功能管理平台。本系统采用Spring Boot作为后端服务框架，Vue3构建前端界面，并使用MySQL存储数据，Redis缓存加速。整个应用部署于Docker容器中并通过Kubernetes进行集群管理。

## 技术栈
- **后端**：Java, Spring Boot
- **前端**：JavaScript, Vue3
- **数据库**：MySQL
- **缓存**：Redis
- **容器化**：Docker
- **编排工具**：Kubernetes

## 功能概览
- 用户管理
- 商品管理
- 订单处理
- 会员服务
- 数据分析与报告
- 内容发布

## 开始使用
1. 安装依赖项。
2. 配置环境变量。
3. 启动服务。
4. 访问Web界面。

## 文档目录
- [需求文档](docs/requirements.md)
- [数据库设计](docs/database_design.md)
- [API接口文档](docs/api_documentation.md)
- [部署指南](docs/deployment_guide.md)

---

# docs/requirements.md

## 系统需求文档

### 概述
本文档旨在详细描述美妆管理系统的需求规格，包括但不限于功能、性能、安全性和用户体验等方面的要求。

### 功能模块
#### 1. 用户管理
- 注册新用户
- 登录验证
- 角色权限分配

#### 2. 商品管理
- 添加/编辑/删除商品信息
- 创建/更新商品分类
- 跟踪库存状态

#### 3. 订单流程
- 下单操作
- 在线支付集成
- 物流状态查询

#### 4. 会员计划
- 设定不同级别的会员资格
- 积分制度
- 发放及使用优惠券

#### 5. 统计报表
- 销售业绩统计
- 用户行为分析
- 导出至Excel或PDF格式

#### 6. 内容维护
- 新闻公告撰写
- 广告轮播图片上传

### 性能指标
- 页面加载时间 < 3秒
- 并发访问支持 > 1000人在线
- 数据库查询响应时间 < 100毫秒

### 安全考量
- HTTPS加密传输
- JWT令牌认证
- SQL注入防护

### 用户体验
- 响应式布局适应多种设备
- 友好的错误提示
- 快速搜索功能

---

# docs/database_design.md

## 数据库设计说明

### 表结构定义
```sql
-- 用户表
CREATE TABLE `user` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `username` VARCHAR(50) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `role` ENUM('admin', 'member') DEFAULT 'member',
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 商品表
CREATE TABLE `product` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `description` TEXT,
  `price` DECIMAL(10, 2) NOT NULL,
  `stock` INT NOT NULL,
  `category_id` INT REFERENCES `category`(id),
  `image_url` VARCHAR(255)
);

-- 更多表略...
```

### 关系图
此处省略图形表示，请参考ER图文件。

---

# docs/api_documentation.md

## API接口文档

### 认证相关
- **登录**
  - URL: `/auth/login`
  - Method: POST
  - Request Body: `{ "username": "string", "password": "string" }`
  - Response: `{ "token": "jwt-token" }`

- **注册**
  - URL: `/auth/register`
  - Method: POST
  - Request Body: `{ "username": "string", "password": "string", "email": "string" }`
  - Response: `{ "message": "success" }`

### 商品相关
- **获取所有商品**
  - URL: `/products`
  - Method: GET
  - Query Params: `?page=1&size=10`
  - Response: `[ { "id": 1, "name": "Lipstick", ... }, ... ]`

- **创建新商品**
  - URL: `/products`
  - Method: POST
  - Request Body: `{ "name": "string", "description": "text", "price": 99.99, "stock": 100, "categoryId": 1, "imageUrl": "url" }`
  - Response: `{ "id": 1, "message": "Created" }`

### 更多功能接口请查看完整版文档

---

# docs/deployment_guide.md

## 部署指南

### Docker镜像构建
1. 修改`Dockerfile`以包含应用程序所需的所有依赖。
2. 运行命令`docker build -t beauty-management-system .`来创建镜像。

### Kubernetes配置
1. 根据提供的YAML模板调整`deployment.yaml`和`service.yaml`文件。
2. 应用配置：`kubectl apply -f deployment.yaml` 和 `kubectl apply -f service.yaml`

### 示例YAML文件
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: beauty-mgmt
spec:
  replicas: 3
  selector:
    matchLabels:
      app: beauty-mgmt
  template:
    metadata:
      labels:
        app: beauty-mgmt
    spec:
      containers:
      - name: beauty-mgmt
        image: your-dockerhub-repo/beauty-management-system:latest
        ports:
        - containerPort: 8080
---
apiVersion: v1
kind: Service
metadata:
  name: beauty-mgmt-service
spec:
  type: LoadBalancer
  selector:
    app: beauty-mgmt
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
```

### 注意事项
- 确保所有环境变量已正确设置。
- 监控日志输出以检查任何潜在问题。