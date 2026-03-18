```Dockerfile
# 使用官方的Java基础镜像，这里选择的是OpenJDK 11版本
FROM openjdk:11-jdk-slim

# 设置环境变量，避免时区问题
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 定义工作目录
WORKDIR /app

# 将构建好的jar包复制到容器中
COPY target/makeup-management-system.jar makeup-management-system.jar

# 暴露应用的服务端口
EXPOSE 8080

# 启动应用
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","makeup-management-system.jar"]
```

此Dockerfile适用于已经打包好名为`makeup-management-system.jar`的应用程序。请确保在构建Docker镜像之前，你的项目已经被正确地编译并打包成JAR文件，并且该文件位于`target/`目录下（这是Maven项目的默认输出位置）。如果你使用的是Gradle或其他构建工具，请相应调整路径。此外，根据实际需要可能还需要安装额外的依赖或配置其他环境变量。