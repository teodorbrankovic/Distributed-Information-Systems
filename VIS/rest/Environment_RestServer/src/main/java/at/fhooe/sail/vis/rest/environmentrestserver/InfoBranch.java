package at.fhooe.sail.vis.rest.environmentrestserver;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.eclipse.persistence.jaxb.MarshallerProperties;

import java.awt.*;
import java.io.StringWriter;
import java.text.ParseException;

@Path("/info")
public class InfoBranch {

    public static Sensors sensors = new Sensors(new String[]{"humidity"});

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getLandingPage() {
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.TEXT_HTML);
        bob.entity("<html> <head> <title>EnvironmentRestServer</title> </head>"
                + "<body>"
                + " <h1>Info Branch</h1> <br/>"
                + "<a href=\"info/sensortypes?output=XML\">Test XML</a>"
                + "<a href=\"info/sensortypes?output=JSON\">Test JSON</a>"
                + "</body> </html>");
        return bob.build();
    }


    @GET
    @Path("/sensortypes")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSensorTypes(@QueryParam("output") String _output) throws JAXBException {
        String output = _output.toUpperCase();
        Response.ResponseBuilder bob = null;

        if (output.equals("XML")) {
            JAXBContext context = JAXBContext.newInstance(Sensors.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            StringWriter w = new StringWriter();
            m.marshal(sensors, w);
            bob = Response.ok();
            bob.type(MediaType.APPLICATION_XML);
            bob.entity(w.toString());

        } else if (output.equals("JSON")) {
            JAXBContext context = JAXBContext.newInstance(Sensors.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            m.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
            StringWriter w = new StringWriter();
            m.marshal(sensors, w);
            bob = Response.ok();
            bob.type(MediaType.APPLICATION_JSON);
            bob.entity(w.toString());

        } else {
            bob = Response.serverError();
            bob.type(MediaType.TEXT_PLAIN);
            bob.entity(_output + " not supported");
        }
        return bob.build();
    }

}
