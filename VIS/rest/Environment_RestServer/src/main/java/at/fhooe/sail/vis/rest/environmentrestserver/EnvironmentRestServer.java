package at.fhooe.sail.vis.rest.environmentrestserver;


import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
@Path("/")
public class EnvironmentRestServer extends ResourceConfig {

    public EnvironmentRestServer() {
        register(this.getClass());
        register(InfoBranch.class);
        register(DataBranch.class);
        register(ExceptionsBranch.class);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getLandingPage() {
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.TEXT_HTML);
        bob.entity("<html> <head> <title>EnvironmentRestServer</title> </head>"
                + "<body>"
                + " <h1>Info Branch</h1> <br/>"
                + "<a href=\"info\">Info Branch</a>"
                + " <h2>Data Branch</h2> <br/>" +
                "<a href=\"data\">Data Branch</a>"
                + " <h3>Exception Branch</h3> <br/>"
                + "<a href=\"exceptiontest\">Exception Branch</a>"
                + "</body> </html>");

        return bob.build();
    }

    @GET
    @Path("/exceptiontest")
    @Produces(MediaType.TEXT_HTML)
    public Response getExceptionTest() {
        int arr[] = new int[5];
        arr[5] = 50;  // ArrayIndexOutOfBoundsException

        StringBuilder wendy = new StringBuilder();
        wendy.append("<html> <head> <title>EnvironmentRestServer</title> </head>");
        wendy.append("<body> <h1>Exception/Test REST Branch</h1> <br/>");
        wendy.append("</body>");
        wendy.append("</html>");
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.TEXT_HTML);
        bob.entity(wendy.toString());
        return bob.build();
    }


}