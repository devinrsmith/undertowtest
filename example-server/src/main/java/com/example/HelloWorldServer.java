package com.example;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  * @author Stuart Douglas
 *   */
public class HelloWorldServer {

    public static void main(final String[] args) {
        final AtomicInteger count = new AtomicInteger(1);
        final Undertow server = Undertow.builder()
                .addHttpListener(8080, "172.31.26.50")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                        exchange.getResponseSender().send("<h2>Hello redcurrent!</h2><h1>Visitor Count: " + count.getAndIncrement() + "</h1>");
                    }
                }).build();
        server.start();
    }

}
