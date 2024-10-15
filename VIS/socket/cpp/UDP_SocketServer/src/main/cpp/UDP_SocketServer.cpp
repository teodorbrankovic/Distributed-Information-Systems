//
// Created by Teodor Brankovic on 13.12.23.
//

#include "../headers/UDP_SocketServer.h"
#include <iostream> // std::cout, cin
#include <unistd.h> // close
#include <arpa/inet.h> // inet_ntop/inet_atop

#define BUFFER_SIZE 1024
#define BACKLOG 10 // define maximum of waiting clients
/**
UDP_SocketServer::UDP_SocketServer(int port): port(port) {
// Constructor initialization if needed
}

UDP_SocketServer::~UDP_SocketServer() {
// Destructor clean up if needed
}
 */


void UDP_SocketServer::InitializeSocket() {
    udpSocket = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);

    if (udpSocket == -1) {
        std::cout << "Socket has an error!" << std::endl;
    }

    memset(&serverAddr, 0, sizeof(serverAddr)); // clear the struct
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(4949);
    serverAddr.sin_addr.s_addr = INADDR_ANY;

    if (bind(udpSocket, (struct sockaddr *) &serverAddr, sizeof(serverAddr)) < 0) {
        std::cout << "bind has an error!" << std::endl;
    } else {
        std::cout << "bind is successful!" << std::endl;
    }

}

void UDP_SocketServer::CommunicationHandler() {
    bool t = true;
    char msg[BUFFER_SIZE];

    socklen_t clientAddrSize = sizeof(clientAddr);

    while (t) {

        int rcvVal = recvfrom(udpSocket,
                              msg,
                              BUFFER_SIZE,
                              0,
                              (struct sockaddr *) &clientAddr,
                              &clientAddrSize);

        // check "quit", "shutdown", "drop"
        if (strcmp(msg, "quit") == 0) {
            t = false;
        } else if (strcmp(msg, "drop") == 0) {
            t = false;
            int closeVal = close(udpSocket);
            if (closeVal == -1) {
                std::cout << "close has an error!" << std::endl;
            }
        } else if (strcmp(msg, "shutdown") == 0) {
            t = false;
            int closeVal = close(udpSocket);
            if (closeVal == -1) {
                std::cout << "close has an error!" << std::endl;
            }
        }

        if (rcvVal == -1) {
            std::cout << "recvfrom has an error!" << std::endl;
        } else {
            std::cout << "Message from client: " << msg << std::endl;
        }

        if (t) {
            std::string msgReturn = "ECHO: ";
            msgReturn.append(msg); // return client message
            msgReturn.append("\0");
            const char *msg2 = msgReturn.c_str(); // convert string to char array
            int msg2Size = strlen(msg2); // check size of the message
            int toSize = sizeof(sockaddr_in);
            int sendVal = sendto(udpSocket,
                                 msg2,
                                 msg2Size,
                                 0,
                                 (struct sockaddr *) &clientAddr,
                                 clientAddrSize);
            if (sendVal == -1) {
                std::cout << "sendto has an error!" << std::endl;
            }
        }
        memset(msg, 0, BUFFER_SIZE); // clear the buffer

    }
}
