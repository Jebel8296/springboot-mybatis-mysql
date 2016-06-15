package com.xuxg.spring.boot.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xuxg.spring.boot.mybatis.mapper.TbIDAuthMapper;

@SpringBootApplication
public class MySpringApplication implements CommandLineRunner {

	@Autowired
	private TbIDAuthMapper tbIDAuthMapper;

	public static void main(String[] args) {
		SpringApplication.run(MySpringApplication.class, args);
	}

	public void run(String... args) throws Exception {
		System.out.println(this.tbIDAuthMapper.selectById(1));
	}
}
