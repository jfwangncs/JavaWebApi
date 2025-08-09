# 基于 OpenJDK 21 Hotspot 版本
FROM eclipse-temurin:21-jdk

# 设置工作目录
WORKDIR /app

# 复制构建产物（假设 jar 包路径如下，如有不同请修改）
COPY app/build/libs/*.jar app.jar

# 暴露端口（如有不同请修改）
EXPOSE 5000

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]
