package com.cyonakute.config;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

/**
 * Created by Opeyemi.Akinnawo on 4/26/2017.
 */
public class RouteContextHandler {
    public static RoutingContext HandleServerError(RoutingContext context) {
        context.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .setStatusCode(500)
                .end();

        return context;
    }

    public static RoutingContext HandleClientError(RoutingContext context) {
        context.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .setStatusCode(400)
                .end();

        return context;
    }

    public static RoutingContext HandleGeneric(RoutingContext context, int statusCode) {
        context.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .setStatusCode(statusCode)
                .end();

        return context;
    }

    public static RoutingContext HandleGenericWithResult(RoutingContext context, int statusCode, List result) {
        context.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .setStatusCode(statusCode)
                .end(Json.encodePrettily(result));

        return context;
    }
}
