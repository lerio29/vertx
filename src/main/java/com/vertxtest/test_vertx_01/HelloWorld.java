package com.vertxtest.test_vertx_01;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class HelloWorld extends AbstractVerticle {

    final Router router = Router.router(vertx);

    @Override
    public void start() throws Exception {

        router.get("/hello/").handler(
                req -> req.response().putHeader("content-type", "text/html")
                        .end("<html><body><h1>Hello World</h1></body></html>"));

        router.get("/byebye/").handler(
                req -> req.response().putHeader("content-type", "text/html")
                        .end("<html><body><h1>Bye Bye World</h1></body></html>"));

        vertx.createHttpServer().requestHandler(router::accept).listen(8080, res -> {
            if(res.succeeded()){
                System.out.println("success");
            }else{
                System.out.println("failed");
            }
        });
    }
}
