//
// Created by Teodor Brankovic on 14.01.24.
//

#include "../headers/Http_SocketServer.h"


int main(int _argc, char *argv[]) {

    Http_SocketServer *httpSocketServer = new Http_SocketServer();
    httpSocketServer->InitializeSocket();
    httpSocketServer->listenHandler();
    httpSocketServer->CommunicationHandler();
}
