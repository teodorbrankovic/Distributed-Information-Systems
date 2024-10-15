package at.fhooe.sail.vis.environmentrmiserver;

import at.fhooe.sail.vis.environment_i.EnvData;
import at.fhooe.sail.vis.environment_i.IEnvService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;


/**
 * The Environment_RmiServer class is a remote object that implements the IEnvService
 * interface. It's designed to provide environmental data to remote clients via RMI (Remote Method Invocation).
 */
 public class Environment_RmiServer extends UnicastRemoteObject implements IEnvService {

    /**
     * Constructs an Environment_RmiServer object.
     *
     * @throws RemoteException If a remote communication error occurs.
     */
     public Environment_RmiServer() throws RemoteException {
        super();
    }


    public static void main(String[] args) {

        try {
/*
            Scanner scanner = new Scanner(System.in); // get input from user
            ServiceMgmt manager = new ServiceMgmt(); // create service manager

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
                    break;
                } else if (command.equals("Stop")) {
                    manager.stopService();
                    break;
                } else if (command.equals("Quit")) {
                    manager.quit();
                    break;
                } else {
                    System.out.println("This command is not supported!");
                }
            }

 */

            Environment_RmiServer server = new Environment_RmiServer();
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            registry.rebind("Environment", server);
            System.out.println("Server is waiting for queries …");

        } catch (Exception _e) {
            _e.printStackTrace();
        }

    }


    /**
     * Provides a list of available environmental data types.
     *
     * @return An array of Strings representing the types of environmental data available.
     * @throws RemoteException If a remote communication error occurs.
     */
    @Override
    public String[] requestEnvironmentDataTypes() throws RemoteException {
        return new String[]{"air"};
    }

    /**
     * Requests environmental data of a specific type.
     *
     * @param _type The type of environmental data requested.
     * @return An EnvData object containing the requested data.
     * @throws RemoteException If a remote communication error occurs.
     */
    @Override
    public EnvData requestEnvironmentData(String _type) throws RemoteException {
        int randTime = new Random().nextInt(); // randomTime
        int randomNumber = new Random().nextInt(101) + 950; // air pressure 950-1050

        if (_type.equals("air")) {
            return new EnvData("air", randTime, new int[]{randomNumber});
        } else {
            return new EnvData("Error", 404, new int[]{404});
        }
    }

    /**
     * Requests all available environmental data.
     *
     * @return An array of EnvData objects containing all available environmental data.
     * @throws RemoteException If a remote communication error occurs.
     */
    @Override
    public EnvData[] requestAll() throws RemoteException {
        return new EnvData[]{requestEnvironmentData("air")};
    }
}