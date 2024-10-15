//
// Created by Teodor Brankovic on 12.12.23.
//

#ifndef VIS_ECHO_SOCKETSERVER_H
#define VIS_ECHO_SOCKETSERVER_H

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

/**
 * @class Echo_SocketServer
 * @brief Class for a simple echo server.
 *
 * This class provides functions to initialize the socket, listen for connections,
 * and communicate with clients.
 */
class Echo_SocketServer {
public:


    /**
     * @brief Initializes the server socket and binds it to an address and port.
     *
     * This function must be called before the server starts listening for connections
     * or communicates with clients.
     */
    void InitializeSocket(); // init, bind

    /**
     * @brief Handler function for listening to connections.
     *
     * This function waits for incoming connections and performs the necessary actions
     * when a connection is established.
     */
    void listenHandler();

    /**
     * @brief Handler function for communication with a connected client.
     *
     * This function handles communication with a client after a successful connection
     * has been established.
     */
    void CommunicationHandler();

private:

    int serverSocket; ///< The server socket.
    sockaddr_in serverAddress; ///< The server's address.
    int port; ///< The port to which the server is bound.
    sockaddr_in clientAddr; ///< The address of the connected client.
    int commSocket; ///< The communication socket for the connected client.

};


#endif //VIS_ECHO_SOCKETSERVER_H
