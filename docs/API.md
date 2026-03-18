# 美妆管理系统 API 文档

## 1. 用户管理

### 1.1 注册用户
- **URL**: `/api/user/register`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "username": "string",
    "password": "string",
    "email": "string"
  }
  ```
- **Response**:
  - **Success (201 Created)**:
    ```json
    {
      "message": "User registered successfully",
      "userId": "string"
    }
    ```
  - **Error (400 Bad Request)**:
    ```json
    {
      "error": "Invalid request data"
    }
    ```

### 1.2 登录用户
- **URL**: `/api/user/login`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **Response**:
  - **Success (200 OK)**:
    ```json
    {
      "token": "string"
    }
    ```
  - **Error (401 Unauthorized)**:
    ```json
    {
      "error": "Invalid credentials"
    }
    ```

### 1.3 获取用户信息
- **URL**: `/api/user/{userId}`
- **Method**: `GET`
- **Headers**:
  - `Authorization: Bearer <token>`
- **Response**:
  - **Success (200 OK)**:
    ```json
    {
      "userId": "string",
      "username": "string",
      "email": "string"
    }
    ```
  - **Error (401 Unauthorized)**:
    ```json
    {
      "error": "Unauthorized"
    }
    ```

## 2. 产品管理

### 2.1 创建产品
- **URL**: `/api/product`
- **Method**: `POST`
- **Headers**:
  - `Authorization: Bearer <token>`
- **Request Body**:
  ```json
  {
    "name": "string",
    "description": "string",
    "price": "number",
    "stock": "number",
    "category": "string"
  }
  ```
- **Response**:
  - **Success (201 Created)**:
    ```json
    {
      "productId": "string",
      "message": "Product created successfully"
    }
    ```
  - **Error (400 Bad Request)**:
    ```json
    {
      "error": "Invalid request data"
    }
    ```

### 2.2 获取产品列表
- **URL**: `/api/products`
- **Method**: `GET`
- **Query Parameters**:
  - `page` (optional): `number` (默认为 1)
  - `size` (optional): `number` (默认为 10)
- **Response**:
  - **Success (200 OK)**:
    ```json
    {
      "products": [
        {
          "productId": "string",
          "name": "string",
          "description": "string",
          "price": "number",
          "stock": "number",
          "category": "string"
        }
      ],
      "totalPages": "number",
      "currentPage": "number"
    }
    ```

### 2.3 更新产品
- **URL**: `/api/product/{productId}`
- **Method**: `PUT`
- **Headers**:
  - `Authorization: Bearer <token>`
- **Request Body**:
  ```json
  {
    "name": "string",
    "description": "string",
    "price": "number",
    "stock": "number",
    "category": "string"
  }
  ```
- **Response**:
  - **Success (200 OK)**:
    ```json
    {
      "message": "Product updated successfully"
    }
    ```
  - **Error (400 Bad Request)**:
    ```json
    {
      "error": "Invalid request data"
    }
    ```

### 2.4 删除产品
- **URL**: `/api/product/{productId}`
- **Method**: `DELETE`
- **Headers**:
  - `Authorization: Bearer <token>`
- **Response**:
  - **Success (200 OK)**:
    ```json
    {
      "message": "Product deleted successfully"
    }
    ```
  - **Error (404 Not Found)**:
    ```json
    {
      "error": "Product not found"
    }
    ```

## 3. 订单管理

### 3.1 创建订单
- **URL**: `/api/order`
- **Method**: `POST`
- **Headers**:
  - `Authorization: Bearer <token>`
- **Request Body**:
  ```json
  {
    "userId": "string",
    "products": [
      {
        "productId": "string",
        "quantity": "number"
      }
    ]
  }
  ```
- **Response**:
  - **Success (201 Created)**:
    ```json
    {
      "orderId": "string",
      "message": "Order created successfully"
    }
    ```
  - **Error (400 Bad Request)**:
    ```json
    {
      "error": "Invalid request data"
    }
    ```

### 3.2 获取订单列表
- **URL**: `/api/orders`
- **Method**: `GET`
- **Headers**:
  - `Authorization: Bearer <token>`
- **Query Parameters**:
  - `page` (optional): `number` (默认为 1)
  - `size` (optional): `number` (默认为 10)
- **Response**:
  - **Success (200 OK)**:
    ```json
    {
      "orders": [
        {
          "orderId": "string",
          "userId": "string",
          "products": [
            {
              "productId": "string",
              "quantity": "number"
            }
          ],
          "createdAt": "string"
        }
      ],
      "totalPages": "number",
      "currentPage": "number"
    }
    ```

### 3.3 获取单个订单
- **URL**: `/api/order/{orderId}`
- **Method**: `GET`
- **Headers**:
  - `Authorization: Bearer <token>`
- **Response**:
  - **Success (200 OK)**:
    ```json
    {
      "orderId": "string",
      "userId": "string",
      "products": [
        {
          "productId": "string",
          "quantity": "number"
        }
      ],
      "createdAt": "string"
    }
    ```
  - **Error (404 Not Found)**:
    ```json
    {
      "error": "Order not found"
    }
    ```

### 3.4 更新订单状态
- **URL**: `/api/order/{orderId}/status`
- **Method**: `PUT`
- **Headers**:
  - `Authorization: Bearer <token>`
- **Request Body**:
  ```json
  {
    "status": "string" // 可选值: "PENDING", "SHIPPED", "DELIVERED", "CANCELLED"
  }
  ```
- **Response**:
  - **Success (200 OK)**:
    ```json
    {
      "message": "Order status updated successfully"
    }
    ```
  - **Error (400 Bad Request)**:
    ```json
    {
      "error": "Invalid request data"
    }
    ```

### 3.5 删除订单
- **URL**: `/api/order/{orderId}`
- **Method**: `DELETE`
- **Headers**:
  - `Authorization: Bearer <token>`
- **Response**:
  - **Success (200 OK)**:
    ```json
    {
      "message": "Order deleted successfully"
    }
    ```
  - **Error (404 Not Found)**:
    ```json
    {
      "error": "Order not found"
    }
    ```

## 4. 分类管理

### 4.1 获取所有分类
- **URL**: `/api/categories`
- **Method**: `GET`
- **Response**:
  - **Success (200 OK)**:
    ```json
    {
      "categories": [
        "string"
      ]
    }
    ```

### 4.2 添加分类
- **URL**: `/api/category`
- **Method**: `POST`
- **Headers**:
  - `Authorization: Bearer <token>`
- **Request Body**:
  ```json
  {
    "category": "string"
  }
  ```
- **Response**:
  - **Success (201 Created)**:
    ```json
    {
      "message": "Category added successfully"
    }
    ```
  - **Error (400 Bad Request)**:
    ```json
    {
      "error": "Invalid request data"
    }
    ```

### 4.3 删除分类
- **URL**: `/api/category/{category}`
- **Method**: `DELETE`
- **Headers**:
  - `Authorization: Bearer <token>`
- **Response**:
  - **Success (200 OK)**:
    ```json
    {
      "message": "Category deleted successfully"
    }
    ```
  - **Error (404 Not Found)**:
    ```json
    {
      "error": "Category not found"
    }
    ```

## 5. 错误处理

- **400 Bad Request**: 请求数据无效。
- **401 Unauthorized**: 未授权访问。
- **404 Not Found**: 资源未找到。
- **500 Internal Server Error**: 服务器内部错误。

请确保在生产环境中使用适当的日志记录和监控工具来跟踪和处理这些错误。