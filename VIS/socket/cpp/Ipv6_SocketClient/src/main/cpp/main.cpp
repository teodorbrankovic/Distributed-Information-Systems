//
// Created by Teodor Brankovic on 16.12.23.
//


#include "Ipv6_SocketClient.h"

int main(int _argc, char *argv[]) {

    Ipv6_SocketClient *ipv6SocketClient = new Ipv6_SocketClient();
    ipv6SocketClient->InitializeSocket();
}