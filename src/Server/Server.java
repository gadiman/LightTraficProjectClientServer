package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * It represent a parallel server.
 *
 * @author Arie and Gad.
 */
class Server extends Thread  {
    private int DEFAULT_PORT = 50000;
    private ServerSocket listenSocket;
    private Socket clientSockets;
    private serverGUI serverGui;
    private Dialog78 dialog78;
    private ArrayList<Dialog78> clients;
//    String line;
    private static Integer i;

    /**
     * Constructor of a TCP server.
     */
    public Server() {
        try {
            listenSocket = new ServerSocket(DEFAULT_PORT);
        } catch (IOException e) { // Error.
            System.out.println("Problem creating the server socket.");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        System.out.println("Server starts on port " + DEFAULT_PORT);
        serverGui = new serverGUI();
        clients = new ArrayList<>();
        start();
        i = 0;
    }

    public void run() {
        try {
            while (true) {
                clientSockets = listenSocket.accept();
                dialog78 = new Dialog78(clientSockets, this);
                clients.add(dialog78);
                serverGui.addDialog(dialog78);
                System.out.println(++i);
                dialog78.bufferSocketOut.println(i.toString());
            }
        } catch (IOException e) {
            System.out.println("Problem listening server socket.");
            System.exit(1);
        }

        System.out.println("End of the server.");
    }
}
