package com.vertxtest.vertx_01;

public class MonImpl implements MonInterface{

    @Override
    public String myMethod(String str) {
        System.out.println(str);
        return str;
    }

}
