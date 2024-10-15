//
// Created by Teodor Brankovic on 13.12.23.
//

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
    int clientSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP); // Adresse Family, Socket Type, Protocol

    // check socket error
    if (clientSocket == -1) {
        cout << "clientSocket has an error!" << endl;
    }

    // Connect to server
    sockaddr_in serverAddress; // struc sockaddr_in, "in" for internet
    serverAddress.sin_family = AF_INET; // sin_family is always AF_INET
    serverAddress.sin_port = htons(4949); // htons converts the port number to network byte order
    serverAddress.sin_addr.s_addr = inet_addr("127.0.0.1"); // INADDR_ANY is the IP address of the machine
    memset(&(serverAddress.sin_zero), '\0', 8); // zero the rest of the struct

    int connectVal = connect(clientSocket, (const struct sockaddr *) &serverAddress, sizeof(sockaddr_in));

    bool t = true;
    while (t) {

        // send message to server
        string msgConsole;
        cout << "Enter message to send to server: ";
        getline(cin, msgConsole);
        msgConsole.append("\0");
        const char *msg = msgConsole.c_str();
        int msgSize = strlen(msg);
        int sendVal = send(clientSocket, msg, msgSize, 0);

        // check send error
        if (sendVal == -1) {
            cout << "send has an error!" << endl;
        }

        if (msgConsole == "quit") {
            t = false;
            int closeVal = close(clientSocket);
            if (closeVal == -1) {
                cout << "close has an error!" << endl;
            }
        } else if (msgConsole == "drop") {
            int sendVal2 = send(clientSocket, msg, msgSize, 0);
            if (sendVal2 == -1) {
                cout << "send has an error!" << endl;
            }
        } else if (msgConsole == "shutdown") {
            int sendVal2 = send(clientSocket, msg, msgSize, 0);
            if (sendVal2 == -1) {
                cout << "send has an error!" << endl;
            }
        }


        char buffer2[BUFFER_SIZE];
        if (t) {
            // receive message from server
            int recvVal = recv(clientSocket, buffer2, BUFFER_SIZE, 0);

            // check recv error
            if (recvVal == -1) {
                cout << "recv has an error!" << endl;
            }
            cout << "Message from server: " << buffer2 << endl;
        }
        memset(buffer2, 0, BUFFER_SIZE); // clear buffer


    } // end while

}