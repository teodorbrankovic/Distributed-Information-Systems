//
// Created by Teodor Brankovic on 13.12.23.
//

#ifndef VIS_UDP_SOCKETCLIENT_H
#define VIS_UDP_SOCKETCLIENT_H


#include <netinet/in.h>

class UDP_SocketClient {
public:

    //explicit UDP_SocketClient(int port);
    //~UDP_SocketClient();
    /**
     * @brief Initializes the UDP socket for the client.
     *
     * This function must be called before the client starts communication with the server.
     */
    void InitializeSocket(); // init

    /**
     * @brief Handler function for communication with the server using UDP.
     *
     * This function handles the reception and transmission of data with the server
     * using the UDP protocol.
     */
    void CommunicationHandler(); // rcv, snd

private:
    int port;
    int udpSocket; ///< The UDP socket for the client.
    sockaddr_in serverAddr; ///< The address of the server to communicate with.
    sockaddr_in clientAddr; ///< The client's address.
};


#endif //VIS_UDP_SOCKETCLIENT_H
