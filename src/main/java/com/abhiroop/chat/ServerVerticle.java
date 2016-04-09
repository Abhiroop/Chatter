package com.abhiroop.chat;



import java.io.File;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;

/*
 * Design and implement a basic mobile messaging server. The server handles following:

1. Maintains a persistent socket connection to every mobile device that has requested a connection to the server. Preferably assigns a destinationId to each mobile device which maps to its socket connection.
2. Routes messages tagged with the destinationId to a particular mobile device.
3. Does not send duplicate messages. Duplicate messages are defined as ((“message1, destinatioId1” == “message2, destinationId2”) && (arrivalTime2-arrivalTime1)<= 5sec)
You are free to implement it in any language and framework but the submission itself should be accompanied with a design doc that outlines the components, sequence diagrams and data storage pieces.

Instead of mobile devices, there could be emulating threads(OR any set of agents capable of making socket connections, breaking, remaking connections etc) in a test suite proxying for real devices.

While the implementation is important, sharp focus on the design in terms of robustness, fault tolerance, scalability and efficiency of the system is what we are particularly looking for. For example a realistic implementation in a limited time frame can accommodate only above mentioned requirements but the design should be able to handle all the requirements and edge cases that a real mobile messaging server needs to handle.
 */

public class ServerVerticle extends Verticle {
	
	@Override
	public void start(){
		
		Logger logger = container.logger();
		
		
		//HTTP Server
		RouteMatcher routeMatcher =new RouteMatcher().get("/", new Handler<HttpServerRequest>() {
			public void handle(HttpServerRequest event) {
				event.response().sendFile("web/display.html");
			}
		}).get(".*\\.(css|js)$",new Handler<HttpServerRequest>() {

			public void handle(HttpServerRequest event) {
				event.response().sendFile("web/"+new File(event.path()));
			}
		});
		vertx.createHttpServer().requestHandler(routeMatcher).listen(8080,"localhost");
		
	}

}
