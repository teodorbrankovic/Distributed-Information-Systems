//
// Created by Teodor Brankovic on 12.12.23.
//
#include "../headers/Echo_SocketServer.h"
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

/*
Echo_SocketServer::Echo_SocketServer(int port): port(port) {
    // Constructor initialization if needed
}

Echo_SocketServer::~Echo_SocketServer() {
   // Destructor clean up if needed
}
 */

void Echo_SocketServer::InitializeSocket() {

    serverSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (serverSocket == -1) {
        cout << "serverSocket has an error!" << endl;
    }

    serverAddress; // struc sockaddr_in, "in" for internet
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
}

void Echo_SocketServer::listenHandler() {
    int rVal2 = listen(serverSocket, BACKLOG);

    // check listen error
    if (rVal2 == -1) {
        cout << "listen has an error!" << endl;
    }

    int clientAddrSize = sizeof(sockaddr_in);
    /*
    commSocket = accept(serverSocket, 0, 0); // 0 = no flags
    cout << "Conection established with client on ... " << endl;
    cout << "SOCKET[client (" << inet_ntoa(clientAddr.sin_addr) << ", " << ntohs(clientAddr.sin_port) <<
         "); server (" << inet_ntoa(serverAddress.sin_addr) << ", " << ntohs(serverAddress.sin_port) << ")]"
         << endl;
         */
}

void Echo_SocketServer::CommunicationHandler() {
    bool t = true;
    bool connected = false;
    while (t) {

        if (connected == false) {
            cout << "Primitive Sockert Server waiting for clients on port:  " << 4949 << " ... " << endl;
            commSocket = accept(serverSocket, 0, 0); // 0 = no flags
            cout << "Conection established with client on ... " << endl;
            cout << "SOCKET[client (" << inet_ntoa(clientAddr.sin_addr) << ", " << ntohs(clientAddr.sin_port) <<
                 "); server (" << inet_ntoa(serverAddress.sin_addr) << ", " << ntohs(serverAddress.sin_port) << ")]"
                 << endl;
            connected = true;
        }

        // RECEIVE, receive data from a socket(client) -> blocks if no data is available!
        char msg[BUFFER_SIZE];
        int rVal3 = recv(commSocket, msg, BUFFER_SIZE, 0); // 0 = no flags

        if (strcmp(msg, "quit") == 0) { // quit
            connected = false;
        } else if (strcmp(msg, "drop") == 0) { // drop
            t = false;
            int rVal5 = close(commSocket);
            if (rVal5 == -1) {
                cout << "close has an error!" << endl;
            }
        } else if (strcmp(msg, "shutdown") == 0) { // shutdown
            t = false;
            int rVal5 = close(commSocket);
            if (rVal5 == -1) {
                cout << "close has an error!" << endl;
            }
            int rVal6 = close(serverSocket);
            if (rVal6 == -1) {
                cout << "close has an error!" << endl;
            }
        }

        if (rVal3 == -1) {
            cout << "recv has an error!" << endl;
        } else if (rVal3 == 0) {
            cout << "Server Error: partner has closed socket!" << endl;
        } else {
            cout << "received: " << msg << endl;
        }


        // SEND, sends data over the socket to the receiver
        if (t) {
            string msgReturn = "ECHO: ";
            msgReturn.append(msg); // return client message
            msgReturn.append("\0");
            const char *msg2 = msgReturn.c_str(); // convert string to char array
            int msg2Size = strlen(msg2); // check size, use debugger to check if size is correct
            int rVal4 = send(commSocket, msg2, msg2Size, 0); // 0 = no flags
            // check send error
            if (rVal4 == -1) {
                cout << "send has an error!" << endl;
            } // else the amount of transfereed bytes
        }

        memset(msg, 0, BUFFER_SIZE); // clear buffer after returned message
    } // end while

    // CLOSE, closes the socket and frees the socket handle
    int rVal5 = close(commSocket);

    // check close error
    if (rVal5 == -1) {
        cout << "close has an error!" << endl;
    }
}


