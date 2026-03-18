```sql
-- data.sql
-- 本文件用于初始化美妆管理系统的数据库数据

-- 初始化用户表
INSERT INTO users (id, username, password, email, role, created_at, updated_at)
VALUES 
    (1, 'admin', '$2a$10$G5zZ8JfQ6s7H.9tLXj4vOeKw3bYkZ1x2iRnMlPqWtCgFvDdEeRrTt', 'admin@example.com', 'ADMIN', NOW(), NOW()),
    (2, 'user1', '$2a$10$G5zZ8JfQ6s7H.9tLXj4vOeKw3bYkZ1x2iRnMlPqWtCgFvDdEeRrTt', 'user1@example.com', 'USER', NOW(), NOW());

-- 初始化品牌表
INSERT INTO brands (id, name, description, created_at, updated_at)
VALUES 
    (1, 'Brand A', 'Description for Brand A', NOW(), NOW()),
    (2, 'Brand B', 'Description for Brand B', NOW(), NOW());

-- 初始化产品类别表
INSERT INTO product_categories (id, name, description, created_at, updated_at)
VALUES 
    (1, 'Skincare', 'Products for skin care', NOW(), NOW()),
    (2, 'Makeup', 'Products for makeup', NOW(), NOW());

-- 初始化产品表
INSERT INTO products (id, brand_id, category_id, name, description, price, stock, created_at, updated_at)
VALUES 
    (1, 1, 1, 'Product A1', 'Description for Product A1', 50.00, 100, NOW(), NOW()),
    (2, 1, 2, 'Product A2', 'Description for Product A2', 30.00, 150, NOW(), NOW()),
    (3, 2, 1, 'Product B1', 'Description for Product B1', 45.00, 80, NOW(), NOW()),
    (4, 2, 2, 'Product B2', 'Description for Product B2', 25.00, 200, NOW(), NOW());

-- 初始化订单表
INSERT INTO orders (id, user_id, total_amount, status, created_at, updated_at)
VALUES 
    (1, 1, 100.00, 'COMPLETED', NOW(), NOW()),
    (2, 2, 75.00, 'PENDING', NOW(), NOW());

-- 初始化订单详情表
INSERT INTO order_details (id, order_id, product_id, quantity, price, created_at, updated_at)
VALUES 
    (1, 1, 1, 2, 50.00, NOW(), NOW()),
    (2, 1, 2, 1, 30.00, NOW(), NOW()),
    (3, 2, 3, 1, 45.00, NOW(), NOW());

-- 初始化评论表
INSERT INTO reviews (id, user_id, product_id, rating, comment, created_at, updated_at)
VALUES 
    (1, 1, 1, 5, 'Great product!', NOW(), NOW()),
    (2, 2, 2, 4, 'Good but a bit expensive.', NOW(), NOW());
```