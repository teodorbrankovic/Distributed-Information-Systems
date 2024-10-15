package at.fhooe.sail.vis.hellormiclient;

import at.fhooe.sail.vis.hellormiinterface.Hello_RmiInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

/**
 * The Hello_RmiClient class is a client for the RMI (Remote Method Invocation) application.
 * It connects to the RMI registry, looks up the remote object by its name, and then
 * invokes a method on that remote object.
 */
public class Hello_RmiClient {

    /**
     * The main method to start the RMI client. It performs the process of connecting
     * to the RMI registry, looking up the remote object, and invoking the remote method.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {


        try {

            // get registry
            Registry reg = LocateRegistry.getRegistry();
            //Amel:
            //Registry reg = LocateRegistry.getRegistry("10.27.98.16");

            // get server object
            Hello_RmiInterface serverObject = (Hello_RmiInterface) reg.lookup("Hello");

            // call server method
            String serverResponse = serverObject.saySomething();

            System.out.println("Server response: "  + serverResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}