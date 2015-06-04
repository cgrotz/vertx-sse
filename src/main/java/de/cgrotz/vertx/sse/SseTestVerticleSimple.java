package de.cgrotz.vertx.sse;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;

/**
 * Created by Christoph Grotz on 04.06.15.
 */
public class SseTestVerticleSimple extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    vertx.createHttpServer().requestHandler(request -> {
      final HttpServerResponse response = request.response();

      if (request.getHeader("Accept").contains("text/html")) {
        response.end(SseIndexHtml.CONTENT);
      } else {
        response.setChunked(true);

        response.headers().add("Content-Type", "text/event-stream;charset=UTF-8");
        response.headers().add("Connection", "keep-alive");

        vertx.eventBus().consumer("events", msg -> {
          response.write("event: message\ndata: " + msg.body() + "\n\n");
        });
      }
    }).listen(8080);

    vertx.setPeriodic(1000, time -> {
      vertx.eventBus().publish("events", "Hello World " + System.currentTimeMillis());
    });
  }
}
