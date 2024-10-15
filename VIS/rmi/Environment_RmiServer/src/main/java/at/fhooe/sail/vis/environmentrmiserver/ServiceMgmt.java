package at.fhooe.sail.vis.environmentrmiserver;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


/**
 * The ServiceMgmt class is responsible for managing a RMI (Remote Method Invocation) service.
 * It allows starting, stopping, and exiting the RMI server for the environment system.
 */
public class ServiceMgmt {


    Environment_RmiServer server;
    Registry reg;


    /**
     * Constructor for ServiceMgmt.
     * Initializes the RMI registry on the default registry port.
     *
     * @throws RemoteException if there is a problem in creating the registry.
     */
    public ServiceMgmt() throws RemoteException {
        reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT); // create registry
    }

    /*
    public static void main (String[] args) {

        try {
            Scanner scanner = new Scanner(System.in); // get input from user
            ServiceMgmt manager = new ServiceMgmt(); // create service manager
            manager.startService();

            while (true) {
                System.out.println("---MENU---");
                System.out.println("Choose a command between: \n" +
                        "Start\n" +
                        "Stop\n" +
                        "Quit\n ");
                System.out.print("Your command: ");
                String command = scanner.nextLine();

                if (command.equals("Start")) {
                    manager.startService();
                } else if (command.equals("Stop")) {
                    manager.stopService();
                } else if (command.equals("Quit")) {
                    manager.quit();
                } else {
                    System.out.println("This command is not supported!");
                }
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    */


    /**
     * Starts the RMI service by binding the Environment_RmiServer to the registry.
     * Outputs to the console when the server is ready for queries.
     */
    public void startService() {
        try {
            server = new Environment_RmiServer();
            reg.rebind("Environment", server); // bind object to registry
            System.out.println("Server is waiting for queries …");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the RMI service by unbinding the Environment_RmiServer from the registry.
     * Outputs to the console when the server is stopped.
     */
    public void stopService() {
        if (reg == null) {
            System.out.println("Server is not running");
            return;
        }

        try {
            reg.unbind("Environment"); // unbind object from registry
            UnicastRemoteObject.unexportObject(server, true); // unexport object
            System.out.println("Server stopped");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Shuts down the RMI service and exits the application.
     * This method unbinds the server from the registry, unexports the server object,
     * and then terminates the program.
     */
    public void quit() {
        try {
            reg.unbind("Environment"); // unbind object from registry
            java.rmi.server.UnicastRemoteObject.unexportObject(server, true); // unexport object
            System.out.println("Server stopping...");
            System.exit(0); // terminate program
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
