package jfwang.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("jfwang.api.mapper")
public class JavaWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaWebApiApplication.class, args);
    }

}
