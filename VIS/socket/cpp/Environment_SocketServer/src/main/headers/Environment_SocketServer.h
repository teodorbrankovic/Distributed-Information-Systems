//
// Created by Teodor Brankovic on 17.12.23.
//

#ifndef VIS_ENVIRONMENT_SOCKETSERVER_H
#define VIS_ENVIRONMENT_SOCKETSERVER_H

#include <string>

using namespace std;

class Environment_SocketServer;

/**
 * @struct CommunicationThreadParams
 * @brief Structure to hold parameters for client communication thread.
 *
 * This structure is used to pass parameters to the client communication thread,
 * including a pointer to the server instance and the client socket.
 */
struct CommunicationThreadParams {
    Environment_SocketServer *server; ///< Pointer to the Environment_SocketServer instance.
    int clientSocket; ///< The client socket for communication.
};

/**
 * @class Environment_SocketServer
 * @brief Class for a socket server environment.
 *
 * This class provides functions to initialize the server socket and handle client communication.
 */
class Environment_SocketServer {
public:

    /**
     * @brief Initializes the server socket.
     *
     * This function must be called before the server starts handling client communication.
     */
    void InitializeSocket();

    /**
     * @brief Static function for client communication thread.
     *
     * This function is called in a separate thread for handling communication with a client.
     *
     * @param _params A pointer to CommunicationThreadParams containing server and client socket information.
     * @return A pointer to the result of the communication thread.
     */
    static void *ClientCommunication(void *_params);

private:
    int serverSocket; ///< The server socket for communication.
};



#endif //VIS_ENVIRONMENT_SOCKETSERVER_H
