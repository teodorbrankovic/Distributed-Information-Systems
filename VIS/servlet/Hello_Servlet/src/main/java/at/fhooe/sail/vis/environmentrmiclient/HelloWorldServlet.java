package at.fhooe.sail.vis.environmentrmiclient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * A simple servlet class extending HttpServlet to demonstrate a basic HTTP servlet.
 * This servlet responds to requests with a simple "Hello World" message and a counter
 * that increments with each request.
 */
@WebServlet(name = "HelloWorldServlet", urlPatterns = {"/hello", "/moin"})
// indicates that the marked class is a servlet
public class HelloWorldServlet extends HttpServlet {
    private int servletCount = 0;

    /**
     * Handles the HTTP GET request. Increments the servlet counter and responds with a basic HTML page
     * containing a "Hello World" greeting and the current count of requests processed by this servlet.
     *
     * @param _request The HttpServletRequest object that contains the request the client has made of the servlet.
     * @param _response The HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws ServletException If the request for the GET could not be handled.
     * @throws IOException If an input or output error is detected when the servlet handles the GET request.
     */
    @Override
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException,
            IOException {
        servletCount++;

        _response.setContentType("text/html"); // MIME-Typ
        PrintWriter out = _response.getWriter(); // output stream
        out.println("<html><head><title>Hello World</title></head><body>");
        out.println("<h1>Hello World</h1>");
        out.println("<p>MointCounter: " + servletCount + " Moins</p>");
        out.println("</body></html>");
    }

}