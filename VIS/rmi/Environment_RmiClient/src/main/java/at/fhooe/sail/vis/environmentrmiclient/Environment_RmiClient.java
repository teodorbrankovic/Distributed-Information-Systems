package at.fhooe.sail.vis.environmentrmiclient;

import at.fhooe.sail.vis.environment_i.EnvData;
import at.fhooe.sail.vis.environment_i.IEnvService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * The Environment_RmiClient class is an RMI client for an environment monitoring service.
 * It connects to the RMI registry, looks up the remote environment service object, and then
 * repeatedly requests environment data.
 */
public class Environment_RmiClient {

    /**
     * The main method to start the RMI client for the environment service. It connects to the
     * RMI registry, looks up the remote environment service, and then enters a loop to
     * continuously request and display environment data.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        try {
            // connect to server
            Registry reg = LocateRegistry.getRegistry();
            IEnvService serverObject = (IEnvService)reg.lookup("Environment");

            while (true) {
                String[] sensors = serverObject.requestEnvironmentDataTypes(); // get all sensors
                for (String sensor : sensors) {
                    EnvData dataO = serverObject.requestEnvironmentData(sensor);
                    System.out.print(dataO);
                    System.out.println();
                    System.out.println("*****************************");
                }
                System.out.println();
                System.out.println();
                EnvData[] dataOs = serverObject.requestAll();
                for (EnvData dataO : dataOs) {
                    System.out.println(dataO);
                } // for data
                try {
                    Thread.sleep(1000);
                } catch (Exception _e) {
                    _e.printStackTrace();
                }
            } // end while

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}