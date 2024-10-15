//
// Created by Teodor Brankovic on 16.12.23.
//

#ifndef VIS_IPV6_SOCKETCLIENT_H
#define VIS_IPV6_SOCKETCLIENT_H


/**
 * @class Ipv6_SocketClient
 * @brief Class for a simple IPv6 client.
 *
 * This class provides a function to initialize the IPv6 socket for the client.
 */
class Ipv6_SocketClient {
public:

    /**
     * @brief Initializes the IPv6 socket for the client.
     *
     * This function must be called before the client starts communication.
     */
    void InitializeSocket();

};


#endif //VIS_IPV6_SOCKETCLIENT_H
