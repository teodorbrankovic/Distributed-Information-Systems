//
// Created by Teodor Brankovic on 16.12.23.
//

#include "../headers/Ipv6_SocketClient.h"

#include <iostream> // cout, cin
#include <sys/socket.h> // socket, bind, listen, accept
#include <netinet/in.h> // IPPROTO_TCP, sockaddr_in, // htons/ntohs, INADDR_ANY

#include <unistd.h> // close
#include <arpa/inet.h> // inet_ntop/inet_atop
#include <string.h> // strlen
#include <semaphore.h> // sem_init

#define BUFFER_SIZE 1024
#define BACKLOG 10 // define maximum of waiting clients

const char *SERVER_ADDRESS = "::1";

using namespace std;

void Ipv6_SocketClient::InitializeSocket() {

    // create socket
    int clientSocket = socket(AF_INET6, SOCK_STREAM, IPPROTO_TCP);

    if (clientSocket < 0) {
        std::cout << "Error while creating socket" << std::endl;
    }

    // server address
    sockaddr_in6 serverAddress = {};
    //memset(&serverAddress, 0, sizeof(serverAddress));
    serverAddress.sin6_family = AF_INET6;
    serverAddress.sin6_port = htons(4949);
    inet_pton(AF_INET6, "::1", &(serverAddress.sin6_addr));

    // connect
    if (connect(clientSocket, (sockaddr *) &serverAddress, sizeof(serverAddress)) < 0 ) {
        std::cout << "Error while connecting" << std::endl;
    } else {
        cout << "Connected to server" << endl;
    }

    // send
    bool t = true;
    while (t) {

        cout << "Enter message for server: ";
        string message;
        getline(cin, message);
        message.append("\0");
        const char *msg = message.c_str();
        send(clientSocket, msg, strlen(msg), 0);

        if (message == "quit") {
            t = false;
            int closeVal = close(clientSocket);
        } else if (message == "drop") {
            t = false;
            int sendVal2 = send(clientSocket, msg, strlen(msg), 0);
        } else if (message == "shutdown") {
            t = false;
            int sendVal2 = send(clientSocket, msg, strlen(msg), 0);
        }

        // receive
        if (t) {
            char buffer[BUFFER_SIZE];
            recv(clientSocket, buffer, BUFFER_SIZE, 0);
            cout << "Received: " << buffer << endl;
            memset(buffer, 0, BUFFER_SIZE); // clear buffer
        }

    } // end while


}
