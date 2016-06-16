package com.xuxg.spring.boot.mybatis.vertx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
			HttpServerResponse response = req.response();
			response.putHeader("content-type", "text/html").end(tbIDAuthMapper.selectById(1).toString());
		});

		server.requestHandler(router::accept).listen(8080);
	}

}
