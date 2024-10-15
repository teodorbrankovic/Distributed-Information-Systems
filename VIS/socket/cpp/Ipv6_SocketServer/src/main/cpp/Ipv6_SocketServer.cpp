//
// Created by Teodor Brankovic on 16.12.23.
//

#include "../headers/Ipv6_SocketServer.h"

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

void Ipv6_SocketServer::InitializeSocket() {

    // create socket
    serverSocket = socket(AF_INET6, SOCK_STREAM, IPPROTO_TCP);

    if (serverSocket < 0) {
        std::cout << "Error while creating socket" << std::endl;
    }

    // set port
    int port = 4949;

    // set server address
    sockaddr_in6 server = {};
    server.sin6_family = AF_INET6;
    server.sin6_flowinfo = 0;
    server.sin6_port = htons(4949);
    server.sin6_scope_id = 0;
    server.sin6_addr = in6addr_any;

    // bind socket
    int bindResult = bind(serverSocket, (sockaddr *) &server, sizeof(server));
    if (bindResult < 0) {
        std::cout << "Error while binding socket" << std::endl;
    }

    // listen
    int listenResult = listen(serverSocket, BACKLOG);
    if (listenResult < 0) {
        std::cout << "Error while listening" << std::endl;
    } else {
        cout << "Server is listening on port " << port << endl;
    }

    // accept
    sockaddr_in6 clientAddress = {};
    int size = sizeof(clientAddress);
    int clientSocket = accept(serverSocket, (sockaddr *) &clientAddress, (socklen_t *) &size);

    if (clientSocket < 0) {
        std::cout << "Error while accepting" << std::endl;
    } else {
        cout << "Client connected" << endl;
    }

    bool t = true;
    while (t) {

        // communication
        char buffer[BUFFER_SIZE];
        ssize_t rcvVal = recv(clientSocket, buffer, BUFFER_SIZE, 0);

        if (rcvVal < 0) {
            std::cout << "Error while receiving" << std::endl;
        } else if (strcmp(buffer, "quit") == 0) {
            t = false;
        } else if (strcmp(buffer, "drop") == 0) {
            t = false;
            close(clientSocket);
        } else if (strcmp(buffer, "shutdown") == 0) {
            t = false;
            close(clientSocket);
            close(serverSocket);
        } else {
            cout << "Received: " << buffer << endl;
        }

        // SEND, sends data over the socket to the receiver
        if (t) {
            string msgReturn = "ECHO: ";
            msgReturn.append(buffer); // return client message
            msgReturn.append("\0");
            const char *msg2 = msgReturn.c_str(); // convert string to char array
            send(clientSocket, msg2, strlen(msg2), 0);
        }

        memset(buffer, 0, BUFFER_SIZE); // clear buffer after returned message
    } // end while

}
