//
// Created by Teodor Brankovic on 17.12.23.
//



#include "../headers/Environment_SocketServer.h"

int main(int _argc, char *argv[]) {

    Environment_SocketServer *environmentSocketServer = new Environment_SocketServer();
    environmentSocketServer->InitializeSocket();

}
