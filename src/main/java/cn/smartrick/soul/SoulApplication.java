package cn.smartrick.soul;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.smartrick.soul.mapper")
public class SoulApplication {
    public static void main(String[] args) {
        SpringApplication.run(SoulApplication.class,args);
    }
}
