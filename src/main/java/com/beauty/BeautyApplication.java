```java
package com.beauty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 美妆管理系统启动类
 * 
 * @author 你的名字
 * @version 1.0
 */
@SpringBootApplication
public class BeautyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeautyApplication.class, args);
    }

    /**
     * 配置CORS过滤器，允许跨域请求
     * 
     * @return CorsFilter
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 允许cookies跨域
        config.addAllowedOrigin("*"); // #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
        config.addAllowedHeader("*"); // #允许访问的头信息,*表示全部
        config.addAllowedMethod("*"); // 允许提交请求的方法，*表示全部允许
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
```