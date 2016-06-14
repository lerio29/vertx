package com.vertxtest.test_vertx_01;

import io.vertx.core.AbstractVerticle;

/**
 * Hello world!
 */
public class MyMainVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        System.out.println("Start of My Main Verticle");

        vertx.deployVerticle("com.vertxtest.test_vertx_01.HelloWorld");

        vertx.deployVerticle("com.vertxtest.test_vertx_01.BlaBlaVerticle");
//        
//        MonInterface monInter =  res -> "coucou";
//        System.out.println("monInter : " + monInter);
//        
//        MonInterface monTest = new MonImpl();
//        monTest.myMethod("lkgjsdfl");
//        System.out.println("monTest : "+monTest);
    }
}
