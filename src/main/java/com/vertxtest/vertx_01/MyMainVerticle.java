package com.vertxtest.vertx_01;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * Hello world!
 */
public class MyMainVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        System.out.println("Start of My Main Verticle");
        
        VertxOptions options = new VertxOptions(); 
        options.setMaxEventLoopExecuteTime(100000);
        vertx = Vertx.vertx(options);

        vertx.deployVerticle("com.vertxtest.vertx_01.HelloWorld");

        vertx.deployVerticle("com.vertxtest.vertx_01.BlaBlaVerticle");
//        
//        MonInterface monInter =  res -> "coucou";
//        System.out.println("monInter : " + monInter);
//        
//        MonInterface monTest = new MonImpl();
//        monTest.myMethod("lkgjsdfl");
//        System.out.println("monTest : "+monTest);
    }
}
