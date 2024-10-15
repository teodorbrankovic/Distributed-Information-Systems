//
// Created by Teodor Brankovic on 16.12.23.
//

#ifndef VIS_IPV6_SOCKETSERVER_H
#define VIS_IPV6_SOCKETSERVER_H


/**
 * @class Ipv6_SocketServer
 * @brief Class for a simple IPv6 server.
 *
 * This class provides functions to initialize the IPv6 server socket and bind it to an address.
 */
class Ipv6_SocketServer {
public:

    /**
     * @brief Initializes the IPv6 server socket and binds it to a specific address.
     *
     * This function must be called before the server starts communication.
     */
    void InitializeSocket(); // init, bind

private:
    int serverSocket; ///< The IPv6 server socket.
};


#endif //VIS_IPV6_SOCKETSERVER_H
