package com.cyonakute.config;

import com.cyonakute.controllers.AnnouncementController;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * Created by Opeyemi.Akinnawo on 4/25/2017.
 */
public class Server extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {
        Router router = Router.router(vertx);

        // Serve static resources from the /assets directory
        router.route("/assets/*").handler(StaticHandler.create("assets"));

        //Announcement section
        router.get("/api/getRecentAnnouncements").handler(AnnouncementController::GetRecentAnnouncements);

        HttpServer server = vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        // Retrieve the port from the configuration,
                        // default to 8080.
                        config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                startFuture.complete();
                            } else {
                                startFuture.fail(result.cause());
                            }
                        }
                );

        System.out.println("MyVerticle started!");
    }

    @Override
    public void stop(Future stopFuture) throws Exception {
        System.out.println("MyVerticle stopped!");
    }

}
