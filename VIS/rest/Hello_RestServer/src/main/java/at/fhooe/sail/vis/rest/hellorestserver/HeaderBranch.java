package at.fhooe.sail.vis.rest.hellorestserver;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/header")
public class HeaderBranch {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getDataPlain() {
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.TEXT_PLAIN);
        bob.entity("<html> <head> <title>HelloRestServer</title> </head>"
                + "<body>"
                + " <h1>Plain page</h1> <br/>"
                + "</body> </html>");
        return bob.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataJSON() {
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.APPLICATION_XML);
        bob.entity("{\"message\" : \"JSON Page\"}");
        return bob.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getDataXML() {
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.APPLICATION_JSON);
        bob.entity("<message>XML Page</message>");
        return bob.build();
    }
}
