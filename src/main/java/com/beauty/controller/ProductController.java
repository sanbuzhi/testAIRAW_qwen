```java
package com.beauty.controller;

import com.beauty.model.Product;
import com.beauty.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品控制器，处理与产品相关的HTTP请求。
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 获取所有产品的列表。
     *
     * @return 产品列表
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    /**
     * 根据ID获取单个产品信息。
     *
     * @param id 产品ID
     * @return 单个产品信息
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    /**
     * 创建一个新的产品。
     *
     * @param product 产品对象
     * @return 创建成功的产品信息
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.save(product);
        return ResponseEntity.ok(createdProduct);
    }

    /**
     * 更新一个已存在的产品信息。
     *
     * @param id      产品ID
     * @param product 更新后的产品对象
     * @return 更新成功的产品信息
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productService.findById(id);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }
        product.setId(id);
        Product updatedProduct = productService.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * 删除一个产品。
     *
     * @param id 产品ID
     * @return 删除状态
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
```