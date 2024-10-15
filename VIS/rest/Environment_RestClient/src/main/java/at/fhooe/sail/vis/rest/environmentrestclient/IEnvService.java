package at.fhooe.sail.vis.rest.environmentrestclient;


import java.rmi.RemoteException;

public interface IEnvService  {
    public String[] requestEnvironmentDataTypes() throws RemoteException;
    public EnvData requestEnvironmentData(String _type) throws RemoteException;
    public EnvData[] requestAll() throws RemoteException;
}
