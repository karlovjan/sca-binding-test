package cz.baros.wildfly.sy.sca.sca_binding_test.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@RequestScoped
@Path("/greetings")
public interface GreetingEndpoint {

	@GET
	@Path("{name}") 
	@Produces(MediaType.TEXT_PLAIN)
	String sayHelloTo(@PathParam("name") String name);
	
}
