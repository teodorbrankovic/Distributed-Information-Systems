package at.fhooe.sail.vis.rest.hellorestserver;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/param")
public class ParameterBranch {


    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getLandingPage() {
        StringBuilder wendy = new StringBuilder();
        wendy.append("<html> <head> <title>HelloRestServer</title> </head>");
        wendy.append("<body> <h1>Parameter REST Branch</h1> <br/>");
        wendy.append("<a href=\"param/url/content/beer\">Type</a> <br/>");
        wendy.append("<a href=\"param/url/parameter?output=beer&stuff=evenMoreBeer\">Parameter</a> <br/>");
        wendy.append("<a href=\"param/jaxb\">JAXB</a> <br/>");
        wendy.append("</body>");
        wendy.append("</html>");
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.TEXT_HTML);
        bob.entity(wendy.toString());
        return bob.build();
    }

    @GET
    @Path("/url/content/{type}")
    @Produces(MediaType.TEXT_HTML)
    public Response getContentXML(@PathParam("type") String _type) {
        StringBuilder wendy = new StringBuilder();
        wendy.append("<html> <head> <title>HelloRestServer</title> </head>");
        wendy.append("<body> <h1>Parameter REST Branch</h1> <br/>");
        wendy.append("<p>Type: " + _type  + "</p>");
        wendy.append("</body>");
        wendy.append("</html>");
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.TEXT_HTML);
        bob.entity(wendy.toString());
        return bob.build();
    }

    @GET
    @Path("/url/parameter")
    @Produces(MediaType.TEXT_HTML)
    public Response getParameterXML(@QueryParam("output") String _output, @QueryParam("stuff") String _stuff) {
        StringBuilder wendy = new StringBuilder();
        wendy.append("<html> <head> <title>HelloRestServer</title> </head>");
        wendy.append("<body> <h1>Parameter REST Branch</h1> <br/>");
        wendy.append("<p>Output: " + _output  + "</p>");
        wendy.append("<p>Stuff: " + _stuff  + "</p>");
        wendy.append("</body>");
        wendy.append("</html>");
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.TEXT_HTML);
        bob.entity(wendy.toString());
        return bob.build();
    }

    @GET
    @Path("/jaxb")
    @Produces({MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.TEXT_PLAIN})
    public Response getPerson(@QueryParam("output") String _output) {
        Response.ResponseBuilder bob = null;
        String output = _output.toUpperCase();
        Person p = new Person("Wendissima", "Wendy");

        switch(output) {
            case "XML" -> {
                bob = Response.ok();
                bob.type(MediaType.APPLICATION_XML);
                bob.entity(p);
            }
            case "JSON" -> {
                bob = Response.ok();
                bob.type(MediaType.APPLICATION_JSON);
                bob.entity(p);
            }
            default -> {
                bob = Response.serverError();
                bob.type(MediaType.TEXT_PLAIN);
                bob.entity("Unknown output format: " + output + " Choose XML or JSON.");
            }
        } // end switch
        return bob.build();
    }

}
