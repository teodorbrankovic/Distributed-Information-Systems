//
// Created by Teodor Brankovic on 16.12.23.
//


#include "../headers/UDP_SocketServer.h"
#include <iostream>

int main(int _argc, char *argv[]) {

    //int port = std::stoi(argv[1]);

    UDP_SocketServer *udpSocketServer = new UDP_SocketServer();
    udpSocketServer->InitializeSocket();
    udpSocketServer->CommunicationHandler();
}