//
// Created by Teodor Brankovic on 14.01.24.
//

#ifndef VIS_HTTP_SOCKETSERVER_H
#define VIS_HTTP_SOCKETSERVER_H

#include <iostream> // cout, cin
#include <sys/socket.h> // socket, bind, listen, accept
#include <netinet/in.h> // IPPROTO_TCP, sockaddr_in, // htons/ntohs, INADDR_ANY

#include <unistd.h> // close
#include <arpa/inet.h> // inet_ntop/inet_atop
#include <string.h> // strlen
#include <semaphore.h> // sem_init

#endif //VIS_HTTP_SOCKETSERVER_H

using namespace std;

/**
 * @class Http_SocketServer
 * @brief The Http_SocketServer class is responsible for handling socket
 *        communication for an HTTP server.
 *
 * This class encapsulates the details of socket programming for a simple
 * HTTP server. It includes methods for initializing the server socket,
 * handling incoming connections, and communicating with connected clients.
 */
class Http_SocketServer {
public:

    /**
     * Initialize the server socket and bind it to a specific port.
     * This method sets up the socket and binds it to the specified address
     * and port number for the server.
     */
    void InitializeSocket();

    /**
    * Listen for incoming connections and accept them.
    * This method puts the server into a listen state where it waits for
    * client connections. On accepting a connection, it will set up a
    * communication socket.
    */
    void listenHandler();

    /**
    * Handle communication with the connected client.
    * This method is responsible for reading requests from and sending
    * responses to the connected client.
    */
    void CommunicationHandler();

private:

    int serverSocket; ///< The server socket.
    sockaddr_in serverAddress; ///< The server's address.
    int port; ///< The port to which the server is bound.
    sockaddr_in clientAddr; ///< The address of the connected client.
    int commSocket; ///< The communication socket for the connected client.
};
