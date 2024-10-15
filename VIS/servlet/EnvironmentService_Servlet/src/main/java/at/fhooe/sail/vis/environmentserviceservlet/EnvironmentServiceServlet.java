package at.fhooe.sail.vis.environmentserviceservlet;

import at.fhooe.sail.vis.environment_i.EnvData;
import at.fhooe.sail.vis.environment_i.IEnvService;
import at.fhooe.sail.vis.environmentrmiserver.Environment_RmiServer;
import at.fhooe.sail.vis.environmentsocketclient.Environment_SocketClient;
import at.fhooe.sail.vis.rest.environmentrestclient.EnvironmentRestClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Vector;

/**
 * The EnvironmentServiceServlet class extends HttpServlet and is designed to gather and display
 * environmental data from both a C++ server (via socket communication) and a Java RMI server.
 * It presents this data in a formatted HTML table, refreshing the data every five seconds.
 */
@WebServlet(name = "EnvironmentService", urlPatterns = {"/envservice", "/environment"})
public class EnvironmentServiceServlet extends HttpServlet {

    /**
     * Handles the HTTP GET request. This method retrieves environmental data from both a socket-based C++ server
     * and a Java RMI server. It then displays this data in an HTML table, including timestamps, sensor names,
     * and sensor values. The page auto-refreshes every five seconds to update the data.
     *
     * @param _request The HttpServletRequest object that contains the request the client has made of the servlet.
     * @param _response The HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws ServletException If the request for the GET could not be handled.
     * @throws IOException If an input or output error is detected when the servlet handles the GET request.
     */
    @Override
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException,
            IOException {

        IEnvService javaClient = new Environment_SocketClient(); // constructor has no params
        Registry registry = null;
        IEnvService rmiServer = null;
        //
        try {
            registry = LocateRegistry.getRegistry();
            rmiServer = (IEnvService) registry.lookup("Environment");
        } catch (Exception e) {
            e.printStackTrace();
        }

        EnvData[] javaClientData = null;

        try {
            javaClientData = javaClient.requestAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        EnvData[] rmiServerData = null;

        try {
            rmiServerData = rmiServer.requestAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        EnvironmentRestClient service = new EnvironmentRestClient();
        at.fhooe.sail.vis.rest.environmentrestclient.EnvData restServer = null;
        try {
            restServer = service.requestEnvironmentData("humidity");
        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintWriter out = _response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Server Data</title>");
        out.println("<script>");
        out.println("setTimeout(function(){ location.reload(); }, 5000);");
        out.println("</script>");
        out.println("<style>");
        out.println("body { font-family: 'Arial', sans-serif; }");
        out.println("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }");
        out.println("th, td { padding: 10px; text-align: left; border: 1px solid #fff; }");
        out.println("th { background-color: #62819F; }");
        out.println("td { background-color: #fff; }");
        out.println("h2 { color: #33475b; }");
        out.println("</style>");
        out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js'></script>");
        out.println("<script>");
        out.println("function formatTimestamp(timestamp) { return moment(timestamp).format('MMMM Do YYYY, h:mm:ss a'); }");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Server Data</h1>");

        if (javaClientData == null) {
            out.println("<p>C++ Server is offline!</p>");
        } else {
            out.println("<h2>C++ Server Data</h2>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Timestamp</th>");
            out.println("<th>Sensor</th>");
            out.println("<th>Value</th>");
            out.println("</tr>");
            for (EnvData data : javaClientData) {
                out.println("<tr>");
                out.println("<td>" + data.getmTimestamp() + "</td>");
                out.println("<td>" + data.getmSensorName() + "</td>");
                out.println("<td>" + Arrays.toString(data.getmValues()) + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }


        if (rmiServerData == null) {
            out.println("<p>RMI Server is offline!</p>");
        } else {
            out.println("<h2>RMI Server Data</h2>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Timestamp</th>");
            out.println("<th>Sensor</th>");
            out.println("<th>Value</th>");
            out.println("</tr>");
            for (EnvData data : rmiServerData) {
                out.println("<tr>");
                out.println("<td>" + data.getmTimestamp() + "</td>");
                out.println("<td>" + data.getmSensorName() + "</td>");
                out.println("<td>" + Arrays.toString(data.getmValues()) + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }

        if (restServer == null) {
            out.println("<p>REST Server is offline!</p>");
        } else {
            out.println("<h2>REST Server Data</h2>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Timestamp</th>");
            out.println("<th>Sensor</th>");
            out.println("<th>Value</th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>" + restServer.getTimestamp() + "</td>");
            out.println("<td>" + restServer.getSensorName() + "</td>");
            out.println("<td>" + Arrays.toString(restServer.getValues()) + "</td>");
            out.println("</tr>");
            out.println("</table>");
        }

        out.println("</body></html>");

    }
}