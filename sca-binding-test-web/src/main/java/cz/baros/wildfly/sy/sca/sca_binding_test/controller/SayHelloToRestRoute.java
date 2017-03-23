package cz.baros.wildfly.sy.sca.sca_binding_test.controller;

import org.apache.camel.builder.RouteBuilder;

public class SayHelloToRestRoute extends RouteBuilder {

	public void configure() {
		
		from("switchyard://SayHelloToRestService")
		.log("Received message for 'SayHelloToRestService' : ${body}")
		.to("switchyard://SayHelloToREF")
		.log("Received message from 'SayHelloToREF' : ${body}");
	}

}
