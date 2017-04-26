package com.cyonakute.config;

import io.vertx.core.Vertx;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Opeyemi.Akinnawo on 4/25/2017.
 */
@Component
public class Runner {

    @PostConstruct
    public void initialize(){
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Server(), handler -> {
            if(handler.succeeded()){

            }
        });
    }
}
