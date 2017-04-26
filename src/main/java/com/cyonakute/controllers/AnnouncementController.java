package com.cyonakute.controllers;

import com.cyonakute.config.MongoDbManager;
import com.cyonakute.models.Announcement;
import com.cyonakute.utilities.Utilities;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;

/**
 * Created by Opeyemi.Akinnawo on 4/25/2017.
 */
public class AnnouncementController {


    private static final String table = "announcement";

    public static void SaveAnnouncement(Announcement announcement) {
        try {
            MongoDbManager dbManager = new MongoDbManager();

            Map objMap = Utilities.ObjectToMap(announcement);
            JsonObject document = new JsonObject(objMap);
            dbManager.getClient().save(table, document, res -> {
                if (res.succeeded()) {

                    String id = res.result();
                    System.out.println("Saved Announcement with id " + id);

                } else {
                    res.cause().printStackTrace();
                }
            });
        }
        catch (Exception exception) {
            System.out.println("Error occured...Announcement not saved..." + exception.getMessage());
        }
    }

    public static void GetAllAnnouncements() {
        try {
            JsonObject query = new JsonObject();
            MongoDbManager dbManager = new MongoDbManager();
            dbManager.getClient().find(table, query, res -> {
                if (res.succeeded()) {
                    for (JsonObject json : res.result()) {
                        System.out.println(json.encodePrettily());
                    }
                } else {
                    res.cause().printStackTrace();
                }
            });
        }
        catch (Exception exception) {
            System.out.println("Error occured...Announcements not found..." + exception.getMessage());
        }
    }

    public static void GetRecentAnnouncements(RoutingContext context) {
        try {
            Vertx.currentContext().executeBlocking(startHandler -> {

                JsonObject query = new JsonObject();
                FindOptions options = new FindOptions();
                options.setLimit(10);
                options.setSort(new JsonObject().put("createdAt", -1));

                MongoDbManager.getClient().findWithOptions(table, query, options, res -> {
                    if (res.succeeded()) {
                        //clean up the object first...
                        for (JsonObject json : res.result()) {
                            json.put("createdAt", json.getJsonObject("createdAt").getString("$date"));
                            json.put("updatedAt", json.getJsonObject("updatedAt").getString("$date"));
                            json.remove("_id");
                            //System.out.println(json.encodePrettily());
                        }

                        context.response()
                                .putHeader("content-type", "application/json; charset=utf-8")
                                .end(Json.encodePrettily(res.result()));
                    } else {
                        context.response()
                                .putHeader("content-type", "application/json; charset=utf-8")
                                .setStatusCode(204)
                                .end();
                        res.cause().printStackTrace();
                    }
                });
            }, true, endHandler -> {

            });
        }
        catch (Exception exception) {
            context.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .setStatusCode(500)
                    .end();
            System.out.println("Error occured...Announcements not found..." + exception.getMessage());
        }
    }
}
