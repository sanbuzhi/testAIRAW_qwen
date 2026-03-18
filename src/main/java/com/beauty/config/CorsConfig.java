```java
package com.beauty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域资源共享（CORS）配置类。
 * 该配置类用于允许来自指定源的跨域请求，以支持前端应用与后端API之间的通信。
 */
@Configuration
public class CorsConfig {

    /**
     * 创建一个CORS过滤器Bean，该过滤器将应用于所有HTTP请求。
     * 
     * @return 配置好的CORS过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        // 创建CORS配置对象
        CorsConfiguration config = new CorsConfiguration();
        
        // 允许任何来源的请求
        config.addAllowedOrigin("*");
        
        // 允许任何头部信息
        config.addAllowedHeader("*");
        
        // 允许任何方法
        config.addAllowedMethod("*");
        
        // 是否允许携带认证信息（如cookies）
        config.setAllowCredentials(true);
        
        // 设置预检请求的有效期，单位为秒
        config.setMaxAge(3600L);

        // 创建URL基于的CORS配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 对所有路径启用CORS
        
        // 返回新的CORS过滤器
        return new CorsFilter(source);
    }
}
```