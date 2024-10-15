//
// Created by Teodor Brankovic on 13.12.23.
//

#include "../headers/UDP_SocketClient.h"
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


void UDP_SocketClient::InitializeSocket() {
    udpSocket = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);

    if (udpSocket == -1) {
        cout << "Socket has an error!" << endl;
    }

    memset(&serverAddr, 0, sizeof(serverAddr)); // clear the struct
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(4949);
    serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");
}

void UDP_SocketClient::CommunicationHandler() {


    bool t = true;
    while (t) {
        string msgConsole;
        cout << "Enter message to send to server: ";
        getline(cin, msgConsole);
        msgConsole.append("\0");
        const char *msg = msgConsole.c_str();
        int msgSize = strlen(msg);
        int sendVal = sendto(udpSocket, msg, msgSize, 0, (struct sockaddr *) &serverAddr, sizeof(serverAddr));

        if (sendVal == -1) {
            cout << "send has an error!" << endl;
        }

        if (msgConsole == "quit") {
            t = false;
            int sendVal = sendto(udpSocket, msg, msgSize, 0, (struct sockaddr *) &serverAddr, sizeof(serverAddr));
            if (sendVal == -1) {
                cout << "send has an error!" << endl;
            }
        } else if (msgConsole == "drop") {
            int sendVal2 = sendto(udpSocket, msg, msgSize, 0, (struct sockaddr *) &serverAddr, sizeof(serverAddr));
            if (sendVal2 == -1) {
                cout << "send has an error!" << endl;
            }
        } else if (msgConsole == "shutdown") {
            int sendVal2 = sendto(udpSocket, msg, msgSize, 0, (struct sockaddr *) &serverAddr, sizeof(serverAddr));
            if (sendVal2 == -1) {
                cout << "send has an error!" << endl;
            }
        }

        char buffer2[BUFFER_SIZE];
        if (t) {
            socklen_t serverAddrSize = sizeof(serverAddr);
            // receive message from server
            int recvVal = recvfrom(udpSocket,
                                   buffer2,
                                   BUFFER_SIZE,
                                   0,
                                   (struct sockaddr *) &serverAddr,
                                    &serverAddrSize);
            // check recv error
            if (recvVal == -1) {
                cout << "recv has an error!" << endl;
            }
            cout << "Message from server: " << buffer2 << endl;
        }
        memset(buffer2, 0, BUFFER_SIZE); // clear buffer

    } // end while


}