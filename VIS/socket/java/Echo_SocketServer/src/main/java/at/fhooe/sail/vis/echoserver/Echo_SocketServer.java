package at.fhooe.sail.vis.echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Echo_SocketServer {

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket mSocket = new ServerSocket(4949); // create a new socket
            System.out.println("waiting for clients... \n");
            Socket sock = mSocket.accept(); // wait for clients to connect
            System.out.println("Client is connected...\n");
            InputStream in = sock.getInputStream(); // get input stream from client
            OutputStream out = sock.getOutputStream(); // get output stream to client;

            boolean connected = true;
            boolean rcv = true;

            while (rcv) {
                if (!connected) {
                    System.out.println("waiting for clients... \n");
                    sock = mSocket.accept(); // wait for clients to connect
                    System.out.println("Client is connected...\n");
                    in = sock.getInputStream(); // get input stream from client
                    out = sock.getOutputStream(); // get output stream to client;
                }

                int data = -1;
                StringBuffer line = new StringBuffer();

                while ((data = in.read()) != -1) { // read data from client
                    if (!(((char) data) == '\n')) {
                        line.append((char) data); // append data to string buffer except '\n'
                    }
                    if (((char) data) == '\n') { // if end of line
                        if (line.toString().equals("quit")) { // if quit
                            connected = false;
                            break;
                        } else if (line.toString().equals("drop")) {
                            System.out.println("dropping connection...");
                            sock.close();
                            rcv = false;
                            break;
                        } else if (line.toString().equals("shutdown")){
                            System.out.println("shutting down server...");
                            sock.close();
                            mSocket.close();
                            rcv = false;
                            break;
                        } else {
                            System.out.println("received: " + line.toString()); // don't add 'n' because client adds it when sending
                            String msg = "ECHO: " + line.toString();
                            msg += '\n';
                            out.write(msg.getBytes()); // send echo to client
                            msg = null;
                            break;
                        }
                    } // if end of line
                } // while data

            } // while true

        } catch (IOException e) { // catch exceptions
            e.printStackTrace();
        }
    }
}
