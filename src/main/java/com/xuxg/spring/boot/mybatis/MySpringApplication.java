package com.xuxg.spring.boot.mybatis;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xuxg.spring.boot.mybatis.consumer.ConTbIDAuthVerticle;
import com.xuxg.spring.boot.mybatis.vertx.MyVerticle;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.impl.zookeeper.ZookeeperClusterManager;

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
		Properties zkConfig = new Properties();

		try {
			zkConfig.load(Object.class.getResourceAsStream("/zookeeper.properties"));
		} catch (Exception e) {
			System.exit(0);
		}

		ClusterManager mgr = new ZookeeperClusterManager(zkConfig);
		VertxOptions options = new VertxOptions().setClusterManager(mgr).setClustered(true);
		Vertx.clusteredVertx(options, res -> {
			if (res.succeeded()) {
				Vertx vertx = res.result();
				vertx.deployVerticle(verticle);
				vertx.deployVerticle(conTbIDAuthVerticle);

				System.out.println("begin succeeded!");

			} else {
				System.out.println("begin failed!");
			}
		});

		// Vertx v = Vertx.vertx();
		// v.deployVerticle(verticle);
		// v.deployVerticle(conTbIDAuthVerticle);
	}
}
