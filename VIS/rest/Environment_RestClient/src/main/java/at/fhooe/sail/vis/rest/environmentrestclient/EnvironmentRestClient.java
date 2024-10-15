package at.fhooe.sail.vis.rest.environmentrestclient;

import jakarta.xml.bind.JAXB;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import javax.xml.transform.stream.StreamSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.RemoteException;

public class EnvironmentRestClient implements IEnvService {

    @Override
    public String[] requestEnvironmentDataTypes() throws RemoteException {

        HttpRequest.Builder bob = HttpRequest.newBuilder();
        bob.uri(URI.create("http://localhost:8080/EnvironmentRestServer/info/sensortypes?output=XML"));
        bob.GET();
        HttpRequest req = bob.build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> respString = null;

        try {
            respString = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException _e) {
            _e.printStackTrace();
        }

        if (respString != null) {
            switch (respString.statusCode()) {
                case 200:
                    StringReader reader = new StringReader(respString.body());
                    Sensors sensors = JAXB.unmarshal(reader, Sensors.class);
                    return sensors.mSensors;
                default:
                    System.out.println("INFO: HTTP status " + respString.statusCode());
                    System.out.println(respString.body());
                    return new String[0];
            } // end switch
        }
        return null;
    }

    @Override
    public EnvData requestEnvironmentData(String _type) throws RemoteException {
        HttpRequest.Builder bob = HttpRequest.newBuilder();
        bob.uri(URI.create("http://localhost:8080/EnvironmentRestServer/data/humidity?output=XML"));
        bob.GET();
        HttpRequest req = bob.build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> respString = null;

        try {
            respString = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException _e) {
            _e.printStackTrace();
        }

        if (respString != null) {
            switch (respString.statusCode()) {
                case 200:
                    StringReader reader = new StringReader(respString.body());
                    EnvData data = JAXB.unmarshal(reader, EnvData.class);
                    return data;
                default:
                    System.out.println("INFO: HTTP status " + respString.statusCode());
                    System.out.println(respString.body());
                    return null;
            } // end switch
        }
        return null;
    }

    @Override
    public EnvData[] requestAll() throws RemoteException {

        HttpRequest.Builder bob = HttpRequest.newBuilder();
        bob.uri(URI.create("http://localhost:8080/EnvironmentRestServer/data/ALL?output=XML"));
        bob.GET();
        HttpRequest req = bob.build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> respString = null;

        try {
            respString = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException _e) {
            _e.printStackTrace();
        }

        if (respString != null) {
            switch (respString.statusCode()) {
                case 200:
                    StringReader reader = new StringReader(respString.body());
                    ListData data = JAXB.unmarshal(reader, ListData.class);
                    return data.getEnvData();
                default:
                    System.out.println("INFO: HTTP status " + respString.statusCode());
                    System.out.println(respString.body());
                    return null;
            } // end switch
        }
        return null;
    }

}