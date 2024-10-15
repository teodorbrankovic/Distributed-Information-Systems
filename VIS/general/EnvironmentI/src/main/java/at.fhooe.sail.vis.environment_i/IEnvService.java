package at.fhooe.sail.vis.environment_i;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEnvService extends Remote {
    // public String[] requestEnvironmentDataTypes();
    // public EnvData requestEnvironmentData(String _type);
    // public EnvData[] requestAll ();

    /**
     * Provides the types of the available environmental sensors
     */
    public String[] requestEnvironmentDataTypes()throws RemoteException;

    /**
     * Provides the measurement values of a specific sensor in the form
     * of an environmental data object (EnvData)
     */
    public EnvData requestEnvironmentData(String _type) throws RemoteException;

    /**
     * Provides the measurement values of all available sensors
     *
     * @return EnvData[] all available measurement values
     */
    public EnvData[] requestAll()throws RemoteException;
}