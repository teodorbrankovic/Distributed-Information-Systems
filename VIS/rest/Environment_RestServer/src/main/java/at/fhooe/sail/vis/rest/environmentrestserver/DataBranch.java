package at.fhooe.sail.vis.rest.environmentrestserver;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.eclipse.persistence.jaxb.MarshallerProperties;


import java.io.StringWriter;
import java.util.Random;
import java.util.StringJoiner;

@Path("/data")
public class DataBranch {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getLandingPage() {
        StringBuilder wendy = new StringBuilder();
        wendy.append("<html> <head> <title>EnvironmentRestServer</title> </head>");
        wendy.append("<body> <h1>Data Branch</h1> <br/>");
        wendy.append("<a href=\"data/humidity?output=XML\">XML Data</a> <br/>");
        wendy.append("<a href=\"data/humidity?output=JSON\">JSON Data</a> <br/>");
        wendy.append("<a href=\"data/ALL?output=XML\">XML ALL Data</a> <br/>");
        wendy.append("<a href=\"data/ALL?output=JSON\">JSON ALL Data</a> <br/>");
        wendy.append("</body>");
        wendy.append("</html>");
        Response.ResponseBuilder bob = Response.ok();
        bob.type(MediaType.TEXT_HTML);
        bob.entity(wendy.toString());
        return bob.build();
    }

    @GET
    @Path("/{Sensor}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSensorData(@PathParam("Sensor") String _sensor, @QueryParam("output") String _output) throws JAXBException {
        Response.ResponseBuilder bob = null;
        String output = _output.toUpperCase();
        Random rand = new Random();
        EnvData data = new EnvData("humidity", System.currentTimeMillis(), new int[]{rand.nextInt(100)});


        if (output.equals("JSON")) {
            if (_sensor.equals("humidity")) {
                JAXBContext context = JAXBContext.newInstance(EnvData.class);
                Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                m.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
                m.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
                StringWriter w = new StringWriter();
                m.marshal(data, w);
                bob = Response.ok();
                bob.type(MediaType.APPLICATION_JSON);
                bob.entity(w.toString());

            } else if (_sensor.equals("ALL")) {
                ListData list = new ListData(3);
                for (int i = 0; i < 3; i++) {
                    list.setEnvData(new EnvData("humidity", System.currentTimeMillis(), new int[]{rand.nextInt(100)}));
                }
                JAXBContext context = JAXBContext.newInstance(ListData.class);
                Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                m.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
                m.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
                StringWriter w = new StringWriter();
                m.marshal(list, w);
                bob = Response.ok();
                bob.type(MediaType.APPLICATION_JSON);
                bob.entity(w.toString());

            } else {
                bob = Response.serverError();
                bob.type(MediaType.TEXT_PLAIN);
                bob.entity(_sensor + " not supported");
            }
        } else if (output.equals("XML")) {
            if (_sensor.equals("humidity")) {
                JAXBContext context = JAXBContext.newInstance(EnvData.class);
                Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                StringWriter w = new StringWriter();
                m.marshal(data, w);
                bob = Response.ok();
                bob.type(MediaType.APPLICATION_XML);
                bob.entity(w.toString());
            } else if (_sensor.equals("ALL")) {
                ListData list = new ListData(3);
                for (int i = 0; i < 3; i++) {
                    list.setEnvData(new EnvData("humidity", System.currentTimeMillis(), new int[]{rand.nextInt(100)}));
                }
                JAXBContext context = JAXBContext.newInstance(ListData.class);
                Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                StringWriter w = new StringWriter();
                m.marshal(list, w);
                bob = Response.ok();
                bob.type(MediaType.APPLICATION_XML);
                bob.entity(w.toString());
            } else {
                bob = Response.serverError();
                bob.type(MediaType.TEXT_PLAIN);
                bob.entity(_sensor + " not supported");
            }
        } else {
            bob = Response.serverError();
            bob.type(MediaType.TEXT_PLAIN);
            bob.entity(_output + " format not supported");
        }

        return bob.build();
    }
}
