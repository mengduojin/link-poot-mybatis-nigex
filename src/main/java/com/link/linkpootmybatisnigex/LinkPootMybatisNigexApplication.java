package com.link.linkpootmybatisnigex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.service","com.controller","com.util"})
@MapperScan(basePackages = {"com.mapper"})
public class LinkPootMybatisNigexApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkPootMybatisNigexApplication.class, args);
    }

}
