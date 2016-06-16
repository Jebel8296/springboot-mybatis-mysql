package com.xuxg.spring.boot.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xuxg.spring.boot.mybatis.consumer.ConTbIDAuthVerticle;
import com.xuxg.spring.boot.mybatis.vertx.MyVerticle;

import io.vertx.core.Vertx;

@SpringBootApplication
public class MySpringApplication implements CommandLineRunner {

	@Autowired
	private MyVerticle verticle;

	@Autowired
	private ConTbIDAuthVerticle conTbIDAuthVerticle;

	public static void main(String[] args) {
		SpringApplication.run(MySpringApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Vertx v = Vertx.vertx();
		v.deployVerticle(verticle);
		v.deployVerticle(conTbIDAuthVerticle);
	}
}
