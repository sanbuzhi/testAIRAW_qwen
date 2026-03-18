# 美妆管理系统部署指南

## 概述
本文件提供了美妆管理系统在生产环境中的部署步骤。请确保您已经具备了必要的权限和资源。

## 系统要求
- Java 11 或更高版本
- Maven 3.6 或更高版本
- Node.js 14 或更高版本
- Nginx 或其他Web服务器
- MySQL 5.7 或更高版本
- Docker (可选)

## 部署步骤

### 1. 准备数据库
1. 创建一个新的MySQL数据库。
2. 导入数据库脚本：
   ```sql
   -- 创建数据库
   CREATE DATABASE beauty_management;

   -- 使用数据库
   USE beauty_management;

   -- 导入表结构和初始数据
   SOURCE /path/to/your/sql/script.sql;
   ```

### 2. 后端部署
1. 克隆项目仓库：
   ```sh
   git clone https://github.com/your-repo/beauty-management.git
   cd beauty-management/backend
   ```

2. 配置后端应用：
   - 修改 `src/main/resources/application.properties` 文件，配置数据库连接和其他必要的参数：
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/beauty_management?useSSL=false&serverTimezone=UTC
     spring.datasource.username=your_db_username
     spring.datasource.password=your_db_password
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
     server.port=8080
     ```

3. 构建并运行后端应用：
   ```sh
   mvn clean install
   java -jar target/beauty-management-backend.jar
   ```

### 3. 前端部署
1. 进入前端目录：
   ```sh
   cd ../frontend
   ```

2. 安装依赖：
   ```sh
   npm install
   ```

3. 配置前端应用：
   - 修改 `src/config/api.js` 文件，配置后端API的URL：
     ```javascript
     export const API_URL = 'http://localhost:8080/api';
     ```

4. 构建前端应用：
   ```sh
   npm run build
   ```

5. 将构建后的文件部署到Web服务器（例如Nginx）：
   - 将 `dist` 目录中的文件复制到Nginx的HTML目录中。
   - 配置Nginx以提供静态文件服务：
     ```nginx
     server {
         listen 80;
         server_name your-domain.com;

         location / {
             root /path/to/your/dist;
             try_files $uri $uri/ /index.html;
         }

         location /api/ {
             proxy_pass http://localhost:8080/;
         }
     }
     ```

### 4. 使用Docker部署（可选）
1. 构建Docker镜像：
   - 在项目根目录下创建 `Dockerfile`：
     ```dockerfile
     # 使用官方Java运行时作为父镜像
     FROM openjdk:11-jre-slim

     # 设置工作目录
     WORKDIR /app

     # 将Maven构建的JAR文件复制到镜像中
     COPY backend/target/beauty-management-backend.jar app.jar

     # 运行JAR文件
     ENTRYPOINT ["java", "-jar", "app.jar"]
     ```

   - 构建Docker镜像：
     ```sh
     docker build -t beauty-management-backend .
     ```

2. 运行Docker容器：
   ```sh
   docker run -d -p 8080:8080 --name beauty-management-backend \
       -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/beauty_management?useSSL=false&serverTimezone=UTC \
       -e SPRING_DATASOURCE_USERNAME=your_db_username \
       -e SPRING_DATASOURCE_PASSWORD=your_db_password \
       beauty-management-backend
   ```

3. 部署前端应用：
   - 在前端目录下创建 `Dockerfile`：
     ```dockerfile
     # 使用官方Node.js运行时作为父镜像
     FROM node:14-alpine as build-stage

     # 设置工作目录
     WORKDIR /app

     # 复制package.json和package-lock.json
     COPY package*.json ./

     # 安装依赖
     RUN npm install

     # 复制项目文件
     COPY . .

     # 构建项目
     RUN npm run build

     # 使用Nginx作为生产环境的Web服务器
     FROM nginx:alpine as production-stage

     # 将构建好的文件复制到Nginx的HTML目录
     COPY --from=build-stage /app/dist /usr/share/nginx/html

     # 替换默认的Nginx配置文件
     COPY ./nginx.conf /etc/nginx/conf.d/default.conf

     # 启动Nginx
     CMD ["nginx", "-g", "daemon off;"]
     ```

   - 构建Docker镜像：
     ```sh
     docker build -t beauty-management-frontend .
     ```

   - 运行Docker容器：
     ```sh
     docker run -d -p 80:80 --name beauty-management-frontend beauty-management-frontend
     ```

### 5. 验证部署
1. 打开浏览器，访问 `http://your-domain.com`，确保前端页面正常显示。
2. 测试后端API，确保所有功能正常工作。

## 常见问题
- **数据库连接失败**：检查数据库配置是否正确，确保数据库服务正在运行。
- **前端无法访问后端API**：检查Nginx配置，确保代理设置正确。
- **Docker容器无法启动**：检查Docker日志，查看具体的错误信息。

## 联系支持
如果您在部署过程中遇到任何问题，请联系我们的技术支持团队：
- 邮箱：support@beauty-management.com
- 电话：+123-456-7890

祝您部署顺利！