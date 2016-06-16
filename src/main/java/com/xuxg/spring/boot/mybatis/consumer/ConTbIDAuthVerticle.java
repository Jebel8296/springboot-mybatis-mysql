package com.xuxg.spring.boot.mybatis.consumer;

import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

@Component
public class ConTbIDAuthVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		vertx.eventBus().consumer("OK-TbIdAuth", handler -> {
			for (int i = 0; i < 100000; i++) {

			}
			JsonObject idAuth = (JsonObject) handler.body();
			System.out.println("收到流水号：" + idAuth.getString("serial_number"));
			handler.reply("100");
		});
	}
}
