# Java Web API

基于 Spring Boot 3 + Java 21 + Gradle 9 的现代化 Java Web API 项目。

## 技术栈

- **Java**: 21
- **构建工具**: Gradle 9
- **框架**: Spring Boot 3.2.8
- **ORM**: MyBatis Plus 3.5.3.2
- **API 文档**: Knife4j 4.4.0
- **代码简化**: Lombok
- **数据库**: MySQL 8.0

## 功能特性

- ✅ RESTful API 设计
- ✅ Swagger/OpenAPI 3.0 接口文档
- ✅ MyBatis Plus 代码生成和增强
- ✅ 分页查询支持
- ✅ 逻辑删除
- ✅ 统一响应格式
- ✅ 参数校验
- ✅ Lombok 简化代码

## 项目结构

```
src/
├── main/
│   ├── java/com/example/api/
│   │   ├── config/          # 配置类
│   │   ├── controller/      # 控制器
│   │   ├── entity/          # 实体类
│   │   ├── mapper/          # Mapper接口
│   │   ├── service/         # 服务层
│   │   └── JavaWebApiApplication.java
│   └── resources/
│       ├── application.yml  # 配置文件
│       └── init.sql        # 数据库初始化脚本
└── test/                   # 测试代码
```

## 快速开始

### 1. 环境要求

- Java 21+
- MySQL 8.0+
- Gradle 9+ (或使用项目自带的 gradlew)

### 2. 数据库配置

1. 创建数据库并导入初始化脚本：

```sql
mysql -u root -p < src/main/resources/init.sql
```

2. 修改 `src/main/resources/application.yml` 中的数据库连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8
    username: your_username
    password: your_password
```

### 3. 运行项目

使用 Gradle 运行：

```bash
./gradlew bootRun
```

或者使用 IDE 直接运行 `JavaWebApiApplication.main()` 方法。

### 4. 访问接口文档

项目启动后，访问以下地址查看 API 文档：

- **Knife4j 文档**: http://localhost:8080/doc.html
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html

### 5. 测试接口

访问健康检查接口：

```bash
curl http://localhost:8080/api/system/health
```

## API 接口

### 系统接口

- `GET /api/system/health` - 健康检查
- `GET /api/system/info` - 系统信息

### 用户管理接口

- `GET /api/users` - 获取用户列表（支持分页和搜索）
- `GET /api/users/{id}` - 根据 ID 获取用户
- `GET /api/users/username/{username}` - 根据用户名获取用户
- `POST /api/users` - 创建用户
- `PUT /api/users/{id}` - 更新用户
- `DELETE /api/users/{id}` - 删除用户
- `DELETE /api/users/batch` - 批量删除用户

## 开发指南

### 添加新的实体和接口

1. 在 `entity` 包下创建实体类，使用 Lombok 注解简化代码
2. 在 `mapper` 包下创建 Mapper 接口，继承 `BaseMapper<T>`
3. 在 `service` 包下创建服务接口和实现类
4. 在 `controller` 包下创建控制器，添加 Swagger 注解

### 配置说明

- **MyBatis Plus**: 自动配置了分页插件和逻辑删除
- **Knife4j**: 增强版 Swagger UI，提供更好的接口文档体验
- **Lombok**: 自动生成 getter/setter、构造函数等模板代码

## 许可证

Apache License 2.0
