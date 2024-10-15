//
// Created by Teodor Brankovic on 16.12.23.
//


#include "../headers/UDP_SocketClient.h"
#include <iostream>

int main(int _argc, char *argv[]) {

    //int port = std::stoi(argv[1]);

    UDP_SocketClient *udpSocketClient = new UDP_SocketClient();
    udpSocketClient->InitializeSocket();
    udpSocketClient->CommunicationHandler();

}