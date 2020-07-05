package com.rc.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.rc.demo.mbg.mapper","com.rc.demo.dao"})
public class MybatisConfig {
}
