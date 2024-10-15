//
// Created by Teodor Brankovic on 16.12.23.
//


#include "Ipv6_SocketServer.h"

int main(int _argc, char *argv[]) {
    Ipv6_SocketServer *ipv6SocketServer = new Ipv6_SocketServer();
    ipv6SocketServer->InitializeSocket();
}
