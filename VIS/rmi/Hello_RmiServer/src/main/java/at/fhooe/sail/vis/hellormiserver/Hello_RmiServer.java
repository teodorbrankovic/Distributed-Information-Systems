package at.fhooe.sail.vis.hellormiserver;

import at.fhooe.sail.vis.hellormiinterface.Hello_RmiInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

/**
 * The Hello_RmiServer class is an implementation of a remote server that
 * provides a simple remote method invocation (RMI) service. This class
 * extends UnicastRemoteObject and implements the Hello_RmiInterface.
 */
public class Hello_RmiServer extends UnicastRemoteObject implements Hello_RmiInterface {

    /**
     * Constructs a Hello_RmiServer instance.
     * This constructor calls the superclass constructor of UnicastRemoteObject.
     *
     * @throws RemoteException if there is a problem with remote object creation.
     */
    public Hello_RmiServer() throws RemoteException {
        super();
    }

    /**
     * Provides a simple remote method that clients can invoke. When called,
     * this method returns a string message.
     *
     * @return A string message, "cookies!".
     * @throws RemoteException if there is a problem with remote method invocation.
     */
    @Override
    public String saySomething() throws RemoteException {
        return "cookies!";
    }

    public static void main(String[] _args) {
        try {

            // create server
            Hello_RmiServer server = new Hello_RmiServer();
            // create registry
            Registry reg =
                    LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            // bind server to registry
            reg.rebind("Hello", server);

            System.out.println("Server is waiting for queries …");
        } catch (Exception _e) {
            _e.printStackTrace();
        }
    }

}