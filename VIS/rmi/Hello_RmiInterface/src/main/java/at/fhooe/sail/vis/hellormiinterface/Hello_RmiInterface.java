package at.fhooe.sail.vis.hellormiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * The Hello_RmiInterface is a remote interface that defines a method
 * for remote method invocation (RMI). This interface extends the
 * java.rmi.Remote interface, which is used to identify interfaces
 * whose methods may be invoked from a non-local virtual machine.
  */
public interface Hello_RmiInterface extends Remote {

    /**
     * Method for remote invocation by RMI clients.
     * When invoked, this method should return a string message.
     *
     * @return A string message as a response to the remote method invocation.
     * @throws RemoteException if there is an error during remote method execution.
     */
    String saySomething() throws RemoteException;
}