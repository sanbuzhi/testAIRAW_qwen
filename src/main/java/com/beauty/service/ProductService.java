```java
package com.beauty.service;

import com.beauty.entity.Product;
import com.beauty.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 产品服务类，用于处理与产品相关的业务逻辑。
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 获取所有产品列表
     *
     * @return 产品列表
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * 根据ID获取单个产品
     *
     * @param id 产品的唯一标识符
     * @return 产品对象，如果不存在则返回空
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * 创建一个新的产品
     *
     * @param product 待创建的产品对象
     * @return 创建后的产品对象
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * 更新一个已存在的产品
     *
     * @param id      产品的唯一标识符
     * @param product 包含更新信息的产品对象
     * @return 更新后的产品对象，如果不存在则返回空
     */
    public Optional<Product> updateProduct(Long id, Product product) {
        return productRepository.findById(id).map(p -> {
            p.setName(product.getName());
            p.setDescription(product.getDescription());
            p.setPrice(product.getPrice());
            p.setStock(product.getStock());
            return productRepository.save(p);
        });
    }

    /**
     * 删除指定ID的产品
     *
     * @param id 产品的唯一标识符
     * @return 如果删除成功返回true，否则返回false
     */
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
```