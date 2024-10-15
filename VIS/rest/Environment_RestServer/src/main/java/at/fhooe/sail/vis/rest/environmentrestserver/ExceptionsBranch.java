package at.fhooe.sail.vis.rest.environmentrestserver;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ExceptionsBranch implements ExceptionMapper<Throwable> {

    @Context
    private UriInfo mUriInfo;


    @Override
    public Response toResponse(Throwable _e) {
        StringBuilder wendy = new StringBuilder();
        wendy.append("<html> <head> <title>EnvironmentRestServer</title> </head>");
        wendy.append("<body> <h1>Exception</h1> <br/>");
        wendy.append("<p>Exception: " + _e.getMessage() + "</p>");
        wendy.append("</body>");
        wendy.append("</html>");

        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.TEXT_HTML);
        bob.entity(wendy.toString());

        return bob.build();
    }
}
