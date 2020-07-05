package com.rc.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.rc.demo.mbg.mapper")
public class MybatisConfig {
}
