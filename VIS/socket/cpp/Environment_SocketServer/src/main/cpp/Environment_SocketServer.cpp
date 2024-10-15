//
// Created by Teodor Brankovic on 17.12.23.
//

#include "../headers/Environment_SocketServer.h"
#include <iostream> // cout, cin
#include <sys/socket.h> // socket, bind, listen, accept
#include <netinet/in.h> // IPPROTO_TCP, sockaddr_in, // htons/ntohs, INADDR_ANY

#include <unistd.h> // close
#include <arpa/inet.h> // inet_ntop/inet_atop
#include <string.h> // strlen
#include <semaphore.h> // sem_init

#define BUFFER_SIZE 1024
#define BACKLOG 10 // define maximum of waiting clients

using namespace std;

void Environment_SocketServer::InitializeSocket() {

    serverSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (serverSocket == -1) {
        cout << "serverSocket has an error!" << endl;
    }

    // BIND procedure, binding the socket to a port and a network address of the machine
    sockaddr_in serverAddress; // struc sockaddr_in, "in" for internet
    serverAddress.sin_family = AF_INET; // sin_family is always AF_INET
    serverAddress.sin_port = htons(4949); // htons converts the port number to network byte order
    serverAddress.sin_addr.s_addr = INADDR_ANY; // INADDR_ANY is the IP address of the machine
    memset(&(serverAddress.sin_zero), '\0', 8); // zero the rest of the struct

    // BIND, binding the socket to a port and a network address of the machine
    socklen_t length = sizeof(sockaddr);
    int rVal = bind(serverSocket, (const struct sockaddr *) &serverAddress, length);

    // check bind error
    if (rVal == -1) {
        cout << "bind has an error!" << endl;
    }

    // LISTEN, shifts socket into listen mode and marks it the "passive" socket
    int rVal2 = listen(serverSocket, BACKLOG);

    // check listen error
    if (rVal2 == -1) {
        cout << "listen has an error!" << endl;
    } else {
        cout << "Environment_SocketServer waiting for clients on port 4949   ... " << endl;
    }

    bool t = true;
    while (t) {
        cout << "Waiting for clients ..." << endl;

        // ACCEPT
        sockaddr_in clientAddr; // address of the client
        int clientAddrSize = sizeof(sockaddr_in);
        int clientSocket = accept(serverSocket, (struct sockaddr *) &clientAddr, (socklen_t *) &clientAddrSize);

        cout << "Client connected" << endl;

        // check accept error
        if (clientSocket == -1) {
            cout << "accept has an error!" << endl;
        } else {
            cout << "Client is connected" << endl;
        }

        CommunicationThreadParams* params = new CommunicationThreadParams();
        params->server = this;
        params->clientSocket = clientSocket;

        pthread_t threadID;
        int threadVal = pthread_create(&threadID, NULL, (void *(*)(void *)) &ClientCommunication, params);
        if (threadVal == -1) {
            cout << "pthread_create has an error!" << endl;
            t = false;
        } else {
            cout << "Thread created" << endl;
        }

    } // end while

}

void *Environment_SocketServer::ClientCommunication(void * _params) {
    CommunicationThreadParams *params = (CommunicationThreadParams *) _params;

    int clientSocketVal = params->clientSocket;
    Environment_SocketServer *server = params->server;

    int receivedBytes = 0;
    bool b = true;
    bool s = true; // bool for sensors
    while (b) {
        char buffer[BUFFER_SIZE];
        receivedBytes = recv(clientSocketVal, buffer, BUFFER_SIZE, 0);

        if (strcmp(buffer, "quit") == 0) {
            b = false;
        } else if (strcmp(buffer, "drop") == 0) {
            b = false;
            if (close(clientSocketVal)) {
                cout << "Clientclose has an error!" << endl;
            } else {
                cout << "Client is getting dropped" << endl;
                break;
            }
        } else if (strcmp(buffer, "shutdown") == 0) {
            b = false; // static val to set while around accept false
            int rVal5 = close(clientSocketVal);
            int rVal6 = close(server->serverSocket);
            if (rVal5 == -1) {
                cout << "Serverclose has an error!" << endl;
            } else {
                cout << "Server is disconnected" << endl;
            }
        } else if (strcmp(buffer, "getSensortypes()#") == 0) {
            string sensorTypes = "light;noise;air#";
            int sentBytes = send(clientSocketVal, sensorTypes.c_str(), strlen(sensorTypes.c_str()), 0);
            if (sentBytes == -1) {
                cout << "send has an error!" << endl;
                b = false;
            }
            s = false;
        } else if (strcmp(buffer, "getSensor(air)#") == 0 || strcmp(buffer, "getSensor(light)#") == 0 ||
                   strcmp(buffer, "getSensor(noise)#") == 0) { // check which type to send
            if (strcmp(buffer, "getSensor(air)#") == 0) {
                int randomTime = rand();
                int randomAir1 = rand() % 100;
                int randomAir2 = rand() % 100;
                int randomAir3 = rand() % 100;
                string airval =
                        to_string(randomTime) + "|" + to_string(randomAir1) + ";" + to_string(randomAir2) + ";" +
                        to_string(randomAir3) + "#";
                int sentBytes = send(clientSocketVal, airval.c_str(), strlen(airval.c_str()), 0);
            } else if (strcmp(buffer, "getSensor(light)#") == 0) {
                int randomTime = rand();
                int randomLight1 = rand() % 100;
                string lightval = to_string(randomTime) + "|" + to_string(randomLight1) + "#";
                int sentBytes = send(clientSocketVal, lightval.c_str(), strlen(lightval.c_str()), 0);
            } else if (strcmp(buffer, "getSensor(noise)#") == 0) {
                int randomTime = rand();
                int randomNoise1 = rand() % 100;
                string noiseval = to_string(randomTime) + "|" + to_string(randomNoise1) + "#";
                int sentBytes = send(clientSocketVal, noiseval.c_str(), strlen(noiseval.c_str()), 0);
            }
            s = false;
        } else if (strcmp(buffer, "getAllSensors()#") == 0) {
            int randomTime = rand();
            int randomAir1 = rand() % 100;
            int randomAir2 = rand() % 100;
            int randomAir3 = rand() % 100;
            int randomLight1 = rand() % 100;
            int randomNoise1 = rand() % 100;
            string allSensors = to_string(randomTime) + "|" + "light;" + to_string(randomLight1) + "|"
                    + "noise;" + to_string(randomNoise1) + "|" + "air;" + to_string(randomAir1) + ";"
                    + to_string(randomAir2) + ";" + to_string(randomAir3) + "#";
            int sentBytes = send(clientSocketVal, allSensors.c_str(), strlen(allSensors.c_str()), 0);
            s = false;
        }

        if (receivedBytes == -1) {
            cout << "recv has an error!" << endl;
            b = false;
        } else if (receivedBytes == 0) {
            cout << "Client is disconnected" << endl;
            b = false;
        } else {
            cout << "received: " << buffer << endl;
        }

        if (b && s) {
            string msgReturn = "ECHO: ";
            msgReturn.append(buffer); // return client message
            int sentBytes = send(clientSocketVal, msgReturn.c_str(), strlen(msgReturn.c_str()), 0);
            if (sentBytes == -1) {
                cout << "send has an error!" << endl;
                b = false;
            }
        }
        s = true;
        memset(buffer, 0, BUFFER_SIZE); // clear buffer after returned message
    } // end while

    // close(clientSocketVal);
    pthread_exit(NULL);
}





