//
// Created by Teodor Brankovic on 13.12.23.
//

#ifndef VIS_UDP_SOCKETSERVER_H
#define VIS_UDP_SOCKETSERVER_H

#include <netinet/in.h>


/**
 * @class UDP_SocketServer
 * @brief Class for a simple UDP server.
 *
 * This class provides functions to initialize the UDP server socket, bind it to an address,
 * and handle communication with clients using UDP.
 */

class UDP_SocketServer {
public:

    //explicit UDP_SocketServer(int port);///< Default constructor for the UDP server.
    //~UDP_SocketServer(); ///< Default destructor for the UDP server.

    /**
     * @brief Initializes the UDP server socket and binds it to a specific address.
     *
     * This function must be called before the server starts communication with clients.
     */
    void InitializeSocket(); // init, bind

    /**
     * @brief Handler function for communication with clients using UDP.
     *
     * This function handles the reception and transmission of data with connected clients
     * using the UDP protocol.
     */
    void CommunicationHandler(); // rcv, snd

private:
    int port; ///< The port number of the UDP server.
    int udpSocket; ///< The UDP server socket.
    sockaddr_in serverAddr; ///< The address of the UDP server.
    sockaddr_in clientAddr; ///< The address of the connected client.
};


#endif //VIS_UDP_SOCKETSERVER_H
