package com.xuxg.spring.boot.mybatis.vertx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuxg.spring.boot.mybatis.domain.TbIDAuth;
import com.xuxg.spring.boot.mybatis.mapper.TbIDAuthMapper;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

@Component
public class MyVerticle extends AbstractVerticle {

	@Autowired
	private TbIDAuthMapper tbIDAuthMapper;

	@Override
	public void start() throws Exception {

		HttpServer server = vertx.createHttpServer();

		Router router = Router.router(vertx);

		router.route("/hello").blockingHandler(req -> {

			TbIDAuth idAuth = tbIDAuthMapper.selectById(1);

			vertx.eventBus().send("OK-TbIdAuth", idAuth.toJsonObject(), reply -> {
				String code = (String) reply.result().body();
				if ("100".equals(code)) {
					System.out.println("对应已处理成功！");
				} else {
					System.out.println("对应已处理失败！");
				}
			});
			HttpServerResponse response = req.response();
			response.putHeader("content-type", "text/html").end(idAuth.toString());
		});

		server.requestHandler(router::accept).listen(8080);
	}

}
