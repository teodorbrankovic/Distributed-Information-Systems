//
// Created by Teodor Brankovic on 28.11.23.
//
#include <iostream> // cout, cin
#include <sys/socket.h> // socket, bind, listen, accept
#include <netinet/in.h> // IPPROTO_TCP, sockaddr_in, // htons/ntohs, INADDR_ANY

#include <unistd.h> // close
#include <arpa/inet.h> // inet_ntop/inet_atop
#include <string.h> // strlen
#include <semaphore.h> // sem_init

#define BUFFER_SIZE 1024

using namespace std;

int main(int _argc, char **_argv) {
    cout << "Hello World" << endl;
}
