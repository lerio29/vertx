package com.vertxtest.vertx_01;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class BlaBlaVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        
        VertxOptions options = new VertxOptions(); 
        options.setMaxEventLoopExecuteTime(100000);
        vertx = Vertx.vertx(options);

        System.out.println("Start of Bla bla bla...");
    }
}
