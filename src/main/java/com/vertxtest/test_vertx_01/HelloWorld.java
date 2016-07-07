package com.vertxtest.test_vertx_01;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.TemplateHandler;
import io.vertx.ext.web.templ.HandlebarsTemplateEngine;
import io.vertx.ext.web.templ.TemplateEngine;

public class HelloWorld extends AbstractVerticle {

    final Router router = Router.router(vertx);

    @Override
    public void start() throws Exception {

        // In order to use a template we first need to create an engine
        final TemplateEngine engine = HandlebarsTemplateEngine.create();
         TemplateHandler handler = TemplateHandler.create(engine);

         router.get("/dynamic/").handler(handler);

         // Route all GET requests for resource ending in .hbs to the template handler
         router.getWithRegex(".+\\.hbs").handler(handler);
         
        // Entry point to the application, this will render a custom template.
//        router.get("/test").handler(ctx -> {
//            // we define a hardcoded title for our application
//                ctx.put("name", "Page de test");
//
////                 and now delegate to the engine to render it.
//                engine.render(ctx, "../index.hbs", res -> {
//                    if (res.succeeded()) {
//                        ctx.response().end(res.result());
//                    } else {
//                        ctx.fail(res.cause());
//                    }
//                });
//            });
//
//         router.get("/hello/").handler(
//         req -> req.response().putHeader("content-type", "text/html")
//         .end("<html><body><h1>Hello World</h1></body></html>"));
//        
//         router.get("/byebye/").handler(
//         req -> req.response().putHeader("content-type", "text/html")
//         .end("<html><body><h1>Bye Bye World</h1></body></html>"));

        vertx.createHttpServer().requestHandler(router::accept).listen(8080, res -> {
            if (res.succeeded()) {
                System.out.println("success: " + res.toString());
            } else {
                System.out.println("failed : " + res.cause());
            }
        });
    }
}
