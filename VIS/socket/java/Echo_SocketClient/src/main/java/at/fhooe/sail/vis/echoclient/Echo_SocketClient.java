package at.fhooe.sail.vis.echoclient;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class Echo_SocketClient {


    public static void main(String[] _argv) {


        try {
            Socket sock = new Socket("127.0.0.1", 4949); // create a new socket
            OutputStream out = sock.getOutputStream(); // get output stream to server
            InputStream in = sock.getInputStream(); // get input stream from server
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // read from console

            StringBuffer line = new StringBuffer();
            while (true) {

                String msg = "";
                System.out.print("Enter message for server: ");
                msg += reader.readLine(); // read from console
                msg += '\n';

                if (msg.equals("quit\n")) {
                    out.write(msg.getBytes()); // send message to server
                    sock.close();
                    break;
                } else if (msg.equals("drop\n")) {
                    out.write(msg.getBytes()); // send message to server
                    break;
                } else if (msg.equals("shutdown\n")) {
                    out.write(msg.getBytes()); // send message to server
                   break;
                } else {
                    out.write(msg.getBytes()); // send message to server
                    msg = null;
                }

                // rcv
                int data = -1;
                line = new StringBuffer();
                while ((data = in.read()) != -1) { // read data from client
                    if (!(((char)data) == '\n')) {
                        line.append((char) data); // append data to string buffer except '\n'
                    }
                    if (((char) data) == '\n') { // if end of line
                        System.out.println("received: " + line.toString());
                        break;
                    } // if end of line

                } // while data
            }
            System.out.println("closing connection...");
            reader.close();
            out.flush(); // flush output stream
            // shutdown
            out.close();
            in.close();
            sock.close();

        } catch (Exception e) { // catch exceptions
            e.printStackTrace();
        }

    } //  end main
}

