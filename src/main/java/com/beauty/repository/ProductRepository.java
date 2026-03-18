```java
package com.beauty.repository;

import com.beauty.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 产品仓库接口，继承自JpaRepository，提供了基本的CRUD操作。
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * 根据产品名称查找产品。
     *
     * @param name 产品名称
     * @return 查找到的产品，如果未找到则返回null
     */
    Product findByName(String name);

    /**
     * 根据产品品牌查找产品列表。
     *
     * @param brand 产品品牌
     * @return 该品牌下的所有产品列表
     */
    Iterable<Product> findByBrand(String brand);

    /**
     * 根据产品类别查找产品列表。
     *
     * @param category 产品类别
     * @return 该类别下的所有产品列表
     */
    Iterable<Product> findByCategory(String category);
}
```