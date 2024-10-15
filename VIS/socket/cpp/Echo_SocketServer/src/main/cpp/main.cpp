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

int main(int _argc, char *argv[]) {
    //int port = std::stoi(argv[1]);

    Echo_SocketServer *echoSocketServer = new Echo_SocketServer();
    echoSocketServer->InitializeSocket();
    echoSocketServer->listenHandler();
    echoSocketServer->CommunicationHandler();
}
