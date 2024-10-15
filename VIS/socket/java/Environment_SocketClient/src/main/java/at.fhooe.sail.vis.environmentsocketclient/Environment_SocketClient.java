package at.fhooe.sail.vis.environmentsocketclient;

import at.fhooe.sail.vis.environment_i.EnvData;
import at.fhooe.sail.vis.environment_i.IEnvService;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class Environment_SocketClient implements IEnvService {

    OutputStream mOut = null;
    InputStream mIn = null;

    public Environment_SocketClient() throws IOException {
        Socket sock = new Socket("127.0.0.1", 4949); // create a new socket
        mOut = sock.getOutputStream(); // get output stream to server
        mIn = sock.getInputStream(); // get input stream from server
    }

    public static void main(String[] _argv) throws IOException {
        System.out.println("starting client...");
        // try {
        IEnvService service = new Environment_SocketClient();
        while (true) {
            String[] sensors = service.requestEnvironmentDataTypes();
            for (String sensor : sensors) {
                EnvData dataO = service.requestEnvironmentData(sensor);
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
            try {
                Thread.sleep(1000);
            } catch (Exception _e) {
                _e.printStackTrace();
            }
        } // while true

    } //  end main

    @Override
    public String[] requestEnvironmentDataTypes() {
        System.out.println("requestEnvironmentDataTypes()#");
        try {
            String msg = "getSensortypes()#\0";
            mOut.write(msg.getBytes()); // send message to server
            // rcv
            int data = -1;
            StringBuffer line = new StringBuffer();
            while (true) {
                data = mIn.read(); // read data from client
                if (data == -1) {
                    System.out.println("connection closed by server");
                    break;
                }
                if (!(((char) data) == '#')) {
                    line.append((char) data); // append data to string buffer except '\n'
                }
                if (((char) data) == '#') { // if end of line
                    break;
                } // if end of line
            } // while data
            return line.toString().split(";");
        } catch (Exception _e) {
            _e.printStackTrace();
        }
        return null;
    }

    @Override
    public EnvData requestEnvironmentData(String _type) {
        EnvData env = null;
        String recvmsg = "";
        System.out.println("getSensor(" + _type + ")#");
        try {
            String msg = "getSensor(" + _type + ")#\0";
            mOut.write(msg.getBytes()); // send message to server
            // rcv
            int data = -1;
            StringBuffer line = new StringBuffer();
            while (true) {
                data = mIn.read(); // read data from client
                if (data == -1) {
                    System.out.println("connection closed by server");
                    break;
                }
                if (!(((char) data) == '#')) {
                    line.append((char) data); // append data to string buffer except '\n'
                }
                if (((char) data) == '#') { // if end of line
                    recvmsg = line.toString();
                    break;
                } // if end of line
            } // while data

            String[] values = recvmsg.split("\\|");
            long timestamp = Long.parseLong(values[0]);
            String sensorValues = values[1].split("#")[0];
            String[] values2 = sensorValues.split(";");
            int[] values4 = new int[values2.length];
            for (int i = 0; i < values2.length; i++) {
                values4[i] = Integer.parseInt(values2[i]);
            }

            env = new EnvData(_type, timestamp, values4);
        } catch (Exception _e) {
            _e.printStackTrace();
        }
        return env;
    }

    @Override
    public EnvData[] requestAll() {
        String[] mAllSensors = requestEnvironmentDataTypes();
        EnvData[] mAllSData = new EnvData[mAllSensors.length];
        for (int i = 0; i < mAllSData.length; i++) {
            mAllSData[i] = requestEnvironmentData(mAllSensors[i]);
        }
        return mAllSData;
    }

}
