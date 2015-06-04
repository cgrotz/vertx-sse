package de.cgrotz.vertx.sse;

import io.vertx.core.Vertx;
import io.vertx.core.spi.VertxFactory;

/**
 * Created by Christoph Grotz on 04.06.15.
 */
public class Boot {

  public static void main(String ... args) {
    Vertx vertx = Vertx.factory.vertx();

    vertx.deployVerticle(SseTestVerticleSimple.class.getName());
  }
}
