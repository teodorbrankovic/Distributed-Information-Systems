package at.fhooe.sail.vis.rest.hellorestserver;


import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest") // http://localhost:8080/Hello_RestServer/rest
@Path("/")
public class HelloRestServer extends ResourceConfig {

    public HelloRestServer () {
        register(this.getClass());
        register(SimpleExceptionsController.class);
        register(ParameterBranch.class);
        register(HeaderBranch.class);
        register(org.glassfish.jersey.moxy.json.MoxyJsonFeature.class);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getLandingPage() {
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.TEXT_HTML);
        bob.entity("<html> <head> <title>HelloRestServer</title> </head>"
                + "<body>"
                + " <h1>Landing Page</h1> <br/>" +
                "<a href=\"rest/test\">Test XML</a>" +
                " <h1>Header</h1> <br/>" +
                "<a href=\"rest/header\">Header</a>" +
                " <h1>Exception</h1> <br/>" +
                "<a href=\"rest/exception\">Exception</a>" +
                " <h1>Paramter</h1> <br/>" +
                "<a href=\"rest/param\">Param</a>"
                + "</body> </html>");
        return bob.build();
    }

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_XML)
    public Response getTest() {
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.APPLICATION_XML);
        bob.entity("<test> <name>Teeeeeeeest</name> </test>");
        return bob.build();
    }

    @GET
    @Path("/exception")
    @Produces(MediaType.APPLICATION_XML)
    public Response getException() throws Exception {
        StringBuilder wendy = new StringBuilder();
        wendy.append("<html> <head> <title>HelloRestServer</title> </head>");
        wendy.append("<body> <h1>Exception REST Branch</h1> <br/>");
        wendy.append("<a href=\"exception/test\">Test exception</a>");
        wendy.append("</body>");
        wendy.append("</html>");
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.TEXT_HTML);
        bob.entity(wendy.toString());
        return bob.build();
    }

    @GET
    @Path("/exception/test")
    @Produces(MediaType.APPLICATION_XML)
    public Response getExceptionTest() throws Exception {

        // create an exception
        int array[] = new int[5];
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }

        StringBuilder wendy = new StringBuilder();
        wendy.append("<html> <head> <title>HelloRestServer</title> </head>");
        wendy.append("<body> <h1>Exception/Test REST Branch</h1> <br/>");
        wendy.append("</body>");
        wendy.append("</html>");
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.TEXT_HTML);
        bob.entity(wendy.toString());
        return bob.build();
    }

}
