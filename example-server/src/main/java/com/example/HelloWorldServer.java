package com.example;

import io.undertow.Handlers;
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
	    .addHttpListener(Integer.parseInt(args[1]), args[0])
                .setHandler(Handlers.path().addExactPath("/", new HttpHandler() {
                    @Override
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                        exchange.getResponseSender().send("<h1>Hello redcurrent!</h1><h3>Visitor Count: " + count.getAndIncrement() + "</h3>");
                    }
                })).build();
        server.start();
    }

}
