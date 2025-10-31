# Copilot instructions for this repo

Spring Boot 3.5 (Java 21) REST API using MyBatis-Plus, Lombok, and Knife4j/OpenAPI. Follow these project-specific patterns.

## Architecture

- Entry: `src/main/java/jfwang/api/ApiApplication.java` (package base `jfwang.api`).
- Structure:
  - `controller/` REST controllers (`ResponseEntity<?>`), Swagger annotations.
  - `entity/` MyBatis-Plus entities with exact table/column names (camel-case mapping disabled).
  - `mapper/` interfaces extend `BaseMapper<T>`; scanned via `@MapperScan("jfwang.api.mapper")` in `MybatisPlusConfig`.
  - `config/` pagination + OpenAPI beans.
- Resources: `application.yml` (port 5000, datasource, mybatis-plus), `logback.xml` (console logger).

## Data access (MyBatis-Plus)

- Mapping is literal (no underscore-to-camel): use real column names in annotations and queries.
  - Example: `@TableName("Users")`, `@TableId(value="Id", type=AUTO)`, field `userName` maps to column `userName`.
  - QueryWrapper example: `new QueryWrapper<User>().eq("userName", username)`.
- CRUD via `BaseMapper<T>`: `selectById`, `selectOne`, `insert`, `updateById`, `deleteById`, batch: `deleteBatchIds(ids)`.
- Pagination enabled: `Page<User> p = new Page<>(current, size); mapper.selectPage(p, wrapper);`.

## API patterns

- Controllers: `@RestController` + `@RequestMapping` + `@Validated`.
  - `UserController` endpoints: list (GET `/api/users` with `current/size`), get by id, create (sets `createdTime/updatedTime`, `state=1`), update by id, delete by id, batch delete.
- Health/version: GET `/version`.

## OpenAPI/Swagger

- Config in `Knife4jConfig` with an `OpenAPI` bean.
- Docs UI when running:
  - `http://localhost:5000/swagger-ui/index.html`
  - `http://localhost:5000/doc.html` (Knife4j)

## Build, run, test

- Use Gradle wrapper (Java 21 toolchain in `build.gradle`). On Windows:
  - Build: `gradlew.bat build`; Run: `gradlew.bat bootRun`; Test: `gradlew.bat test`.
- Jar at `build/libs/*.jar`; Dockerfile copies to `app.jar` and runs on port 5000.
- DB config points to remote MySQL; override via env/profiles for local dev.

## CI/CD & Docker

- Workflow: `.github/workflows/docker.yml` builds with Gradle, builds image `jfwangncs/javawebapi:${{ github.run_number }}`, pushes, then deploys via SSH and runs container `javaapi` mapping `8008->5000`.
- Dockerfile: `eclipse-temurin:21-jdk`, `EXPOSE 5000`, `ENTRYPOINT ["java","-jar","app.jar"]`.

## Authentication (Sa-Token)

- Sa-Token annotation mode is configured for authentication and authorization.
- **JWT Integration**: Uses `sa-token-jwt` with **Stateless mode** for scalable, distributed authentication.
  - JWT tokens contain user info and don't require server-side session storage.
  - Configuration in `SaTokenJwtConfig` with `StpLogicJwtForStateless`.
  - JWT secret key configured in `application.yml` (use secure key in production).
- Key annotations:
  - `@SaCheckLogin` requires user to be logged in.
  - `@SaCheckPermission("permission:code")` checks specific permissions.
- Auth endpoints in `AuthController`:
  - `POST /api/auth/login` (userName, password) → returns JWT token and user info.
  - `POST /api/auth/logout` → requires login, clears session.
  - `GET /api/auth/userInfo` → requires login, returns current user.
  - `GET /api/auth/isLogin` → checks login status.
- All `UserController` endpoints now require login; CUD operations require permissions (`user:add`, `user:edit`, `user:delete`).
- Token configuration: 30-day timeout, stateless JWT mode, concurrent login allowed.

## Logging

- Logback console appender (root=info). `logging.level.jfwang.api=debug` in `application.yml`.

## Gotchas

- Case-sensitive columns; avoid `map-underscore-to-camel-case` assumptions.
- Global logic-delete is configured but `User` lacks `deleted` field; deletes are physical by default.
- No service layer: controllers directly use mappers; follow this pattern unless refactoring.
- Sa-Token imports may show IDE errors but compile/run correctly after dependency refresh.

If any part is unclear or you want examples for adding a new entity + endpoints, ask and we’ll extend this guide.
