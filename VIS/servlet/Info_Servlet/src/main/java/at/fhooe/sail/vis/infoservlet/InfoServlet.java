package at.fhooe.sail.vis.infoservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * The InfoServlet class extends HttpServlet to provide information about each incoming HTTP request.
 * It counts the number of requests received and displays various details about the client making the request.
 */
@WebServlet(name = "InfoServlet", urlPatterns = {"/info", "/infoServlet"})
// indicates that the marked class is a servlet
public class InfoServlet extends HttpServlet {
    private int infoServletCount = 0;

    /**
     * Handles the HTTP GET request. Increments the servlet counter and responds with a basic HTML page
     * containing information about the client making the request.
     *
     * @param _request The HttpServletRequest object that contains the request the client has made of the servlet.
     * @param _response The HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws ServletException If the request for the GET could not be handled.
     * @throws IOException If an input or output error is detected when the servlet handles the GET request.
     */
    @Override
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException,
            IOException {
        infoServletCount++;

        _response.setContentType("text/html"); // MIME-Typ
        PrintWriter out = _response.getWriter(); // output stream
        out.println("<html><head><title>Hello World</title></head><body>");
        out.println("<h1>Info Servlet</h1>");
        out.println("<p>MoinCounter: " + infoServletCount + "</p>");
        out.println("<p>IP of client: " + _request.getRemoteHost() + "</p>");
        out.println("<p>Browser type of client: " + _request.getHeader("User-Agent") + "</p>");
        out.println("<p>MIME types of client: " + _request.getHeader("Accept") + "</p>");
        out.println("<p>Client protocol: " + _request.getProtocol() + "</p>");
        out.println("<p>Client port: " + _request.getServerPort() + "</p>");
        out.println("<p>Server name: " + _request.getServerName() + "</p>");
        out.println("<p>Parameters: " + _request.getParameter("param") + "</p>");
        out.println("</body></html>");
    }

}