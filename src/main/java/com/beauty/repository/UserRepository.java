```java
package com.beauty.repository;

import com.beauty.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户数据访问接口
 * 该接口继承自JpaRepository，提供了基本的CRUD操作
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户实体，如果未找到则返回null
     */
    User findByUsername(String username);

    /**
     * 根据邮箱查找用户
     *
     * @param email 邮箱地址
     * @return 用户实体，如果未找到则返回null
     */
    User findByEmail(String email);

    /**
     * 检查用户名是否唯一
     *
     * @param username 用户名
     * @return 如果用户名已存在则返回true，否则返回false
     */
    boolean existsByUsername(String username);

    /**
     * 检查邮箱是否唯一
     *
     * @param email 邮箱地址
     * @return 如果邮箱已存在则返回true，否则返回false
     */
    boolean existsByEmail(String email);
}
```