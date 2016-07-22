package com.vertxtest.vertx_01;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.templ.HandlebarsTemplateEngine;
import io.vertx.ext.web.templ.TemplateEngine;

public class HelloWorld extends AbstractVerticle {

    public static final String APPLICATION_JSON = "application/json";

    @Override
    public void start() throws Exception {

        VertxOptions options = new VertxOptions();
        options.setMaxEventLoopExecuteTime(100000);
        vertx = Vertx.vertx(options);

        final Router router = Router.router(vertx);

        // Enable multipart form data parsing
        router.route().handler(BodyHandler.create());
        // In order to use a template we first need to create an engine
        final TemplateEngine engine = HandlebarsTemplateEngine.create();
        // TemplateHandler handler = TemplateHandler.create(engine);
        
     // Route all GET requests for resource ending in .hbs to the template
        // handler
        router.getWithRegex(".+\\.hbs").handler(context -> {
            final Session session=context.session();
            context.data().put("userLogin",session.get("login"));
            context.data().put("accessToken",session.get("accessToken"));
            context.next();
        });

        router.get("/dynamic/").handler(ctx -> {

            // variable transmise au template et interpreté avec les balises
            // handleBars {{name}}
                ctx.put("name", "Vert.x Web");

                engine.render(ctx, "templates/test.hbs", res -> {

                    if (res.succeeded()) {
                        ctx.response().end(res.result());
                    } else {
                        ctx.fail(res.cause());
                    }

                });
            });

        router.get("/hello/").handler(
                req -> {
                    req.response().putHeader("content-type", "text/html")
                            .end("<html><body><h1>Hello World</h1></body></html>");
                    System.out.println(req.request().getParam("toto").trim());
                });

        vertx.createHttpServer().requestHandler(router::accept).listen(8989, res -> {
            if (res.succeeded()) {
                System.out.println("success: " + res.toString());
            } else {
                System.out.println("failed : " + res.cause());
            }
        });
    }
}
