package com.feng.mybatisplus;

import com.feng.mybatisplus.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisPlusApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

}
