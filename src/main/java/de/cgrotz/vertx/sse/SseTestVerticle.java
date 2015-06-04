package de.cgrotz.vertx.sse;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * Created by Christoph Grotz on 04.06.15.
 */
public class SseTestVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    Router router = Router.router(vertx);
    router.get("/events").handler(ctx -> {

      ctx.response().setChunked(true);

      ctx.response().headers().add("Content-Type", "text/event-stream;charset=UTF-8");
      ctx.response().headers().add("Connection", "keep-alive");

      vertx.eventBus().consumer("events", msg -> {
        ctx.response().write("event: message\ndata: " + msg.body() + "\n\n");
      });
    });
    router.get().handler(ctx -> {
      ctx.response().end(SseIndexHtml.CONTENT);
    });

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);

    vertx.setPeriodic(1000, time -> {
      vertx.eventBus().publish("events", "Hello World " + System.currentTimeMillis());
    });

  }
}
