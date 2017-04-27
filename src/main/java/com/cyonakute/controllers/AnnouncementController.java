package com.cyonakute.controllers;

import com.cyonakute.config.RouteContextHandler;
import com.cyonakute.config.MongoDbManager;
import com.cyonakute.models.Announcement;
import com.cyonakute.utilities.Utilities;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.web.RoutingContext;

import java.util.Date;
import java.util.Map;

/**
 * Created by Opeyemi.Akinnawo on 4/25/2017.
 */
public class AnnouncementController {


    private static final String table = "announcement";

    public static void AddAnnouncement(RoutingContext context) {
        try {
            Vertx.currentContext().executeBlocking(startHandler -> {

                JsonObject query = new JsonObject();

                MongoDbManager.getClient().count(table, query, res -> {
                    if(res.succeeded()) {
                        try {
                            Announcement announcement = new Announcement(new JsonObject(context.getBodyAsString()));

                            //enter the id value...
                            announcement.setAnnId(res.result().intValue() + 1);

                            Map objMap = Utilities.ObjectToMap(announcement);
                            JsonObject document = new JsonObject(objMap);
                            document.put("createdAt", new JsonObject().put("$date","2017-12-19T06:00:00.000Z"));

                            MongoDbManager.getClient().save(table, document, saveRes -> {
                                if (saveRes.succeeded()) {
                                    RouteContextHandler.HandleGeneric(context,201);
                                } else {
                                    RouteContextHandler.HandleClientError(context);
                                    saveRes.cause().printStackTrace();
                                }
                            });
                        } catch (Exception e) {
                            RouteContextHandler.HandleClientError(context);
                        }
                    }
                    else {
                        RouteContextHandler.HandleGeneric(context,204);
                        res.cause().printStackTrace();
                    }
                });
            }, true, endHandler -> {

            });

        }
        catch (Exception exception) {
            RouteContextHandler.HandleServerError(context);
            System.out.println("Error occured...Announcement not saved..." + exception.getMessage());
        }
    }

    public static void GetAllAnnouncements(RoutingContext context) {
        try {
            Vertx.currentContext().executeBlocking(startHandler -> {

                JsonObject query = new JsonObject();
                FindOptions options = new FindOptions();
                options.setSort(new JsonObject().put("createdAt", -1));

                GetAnnouncements(context, query, options);
            }, true, endHandler -> {

            });
        }
        catch (Exception exception) {
            RouteContextHandler.HandleServerError(context);
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

                GetAnnouncements(context, query, options);
            }, true, endHandler -> {

            });
        }
        catch (Exception exception) {
            RouteContextHandler.HandleServerError(context);
            System.out.println("Error occured...Announcements not found..." + exception.getMessage());
        }
    }

    public static void GetAnnouncements(RoutingContext context, JsonObject query, FindOptions options) {
        try {
            MongoDbManager.getClient().findWithOptions(table, query, options, res -> {
                if (res.succeeded()) {
                    //clean up the object first...
                    for (JsonObject json : res.result()) {
                        json.put("createdAt", json.getJsonObject("createdAt").getString("$date"));
                        //json.put("updatedAt", json.getJsonObject("updatedAt").getString("$date"));
                        json.remove("_id");
                        //System.out.println(json.encodePrettily());
                    }

                    RouteContextHandler.HandleGenericWithResult(context, 200, res.result());
                } else {
                    RouteContextHandler.HandleGeneric(context,204);
                    res.cause().printStackTrace();
                }
            });
        }
        catch (Exception exception) {
            RouteContextHandler.HandleServerError(context);
            System.out.println("Error occured...Announcements not found..." + exception.getMessage());
        }
    }


}
