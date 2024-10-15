package at.fhooe.sail.vis.sessionservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * This servlet is used to display information about the client.
 * It is used in the session example.
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/session", "/sessionServlet"}) // indicates that the marked class is a servlet
public class SessionServlet extends HttpServlet {
    private int infoServletCount = 0;
    private String magicNumber = "";

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param _request an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     *
     * @param _response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException,
            IOException {
        infoServletCount++;

        String param = _request.getParameter("param");

        _response.setContentType("text/html"); // MIME-Typ
        PrintWriter out = _response.getWriter(); // output stream
        out.println("<html><head><title>Hello World</title></head><body>");
        out.println("<h1>Info Servlet</h1>");
        out.println("<p>MoinCounter: " + infoServletCount + "</p>");
        out.println("<p>IP of client: " + _request.getRemoteHost() + "</p>");
        out.println("<p>Browser type of client: " + _request.getHeader("User-Agent")  + "</p>");
        out.println("<p>MIME types of client: " + _request.getHeader("Accept")+ "</p>");
        out.println("<p>Client protocol: " + _request.getProtocol() + "</p>");
        out.println("<p>Client port: " + _request.getServerPort() + "</p>");
        out.println("<p>Server name: " + _request.getServerName() + "</p>");
        out.println("<p>You are currently using " + _request.getHeader("User-Agent") + " (magic number: " +
                _request.getParameter("param") + ") "
                + "Last time you visited, your magic number was " + magicNumber + "!" + "</p>");
        out.println("</body></html>");
        magicNumber = param;
    }

}