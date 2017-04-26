package com.cyonakute.config;

import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Opeyemi.Akinnawo on 4/25/2017.
 */
@Component
public class MongoDbManager {

    private static MongoClient client;

    public static MongoClient getClient() {
        if(client == null) {
            InitializeFields();
        }
        return client;
    }

    public static void InitializeFields() {
        //generate the expected map...
        JsonObject config = new JsonObject();
        config.put("host", "localhost");//environment.getProperty("spring.data.mongodb.host"));
        config.put("port", 27017);//environment.getProperty("spring.data.mongodb.port"));
        config.put("db_name", "cyondb");
        config.put("authSource", "cyondb");//environment.getProperty("spring.data.mongodb.database"));

        client = MongoClient.createShared(Vertx.currentContext().owner(), config);
    }
}
