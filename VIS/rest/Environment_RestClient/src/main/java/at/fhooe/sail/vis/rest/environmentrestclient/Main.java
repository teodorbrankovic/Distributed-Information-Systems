package at.fhooe.sail.vis.rest.environmentrestclient;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.util.Arrays;

public class Main {

    //@SuppressWarnings("deprecation")
    public static void main(String[] args) throws RemoteException {

        EnvironmentRestClient service = new EnvironmentRestClient();

        String[] sensors = service.requestEnvironmentDataTypes();
        System.out.println("TYPE");
        System.out.println(Arrays.toString(sensors));
        System.out.println("DATA");
        EnvData data0 = service.requestEnvironmentData(sensors[0]);
        System.out.println((data0.toString()));
        System.out.println("ALL");
        EnvData[] dataOs = service.requestAll();
        for (EnvData dataO : dataOs) {
            System.out.println(dataO.toString());
        } // for data
        /*
        for (String sensor : sensors) {
            EnvData dataO = service.requestEnvironmentData("humidity");
            System.out.print(dataO);
            System.out.println();
            System.out.println("*****************************");
        } // for sensor
        System.out.println();
        System.out.println();
        EnvData[] dataOs = service.requestAll();
        for (EnvData dataO : dataOs) {
            System.out.println(dataO);
        } // for data
         */

    }
}

