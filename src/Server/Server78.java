package Server;

//file name: Server.java
//Iyar 5770  update Sivan 5778
//Levian Yehonatan
import java.io.*;
import java.net.*;
import java.util.ArrayList;

class Server78 extends Thread 	   //the parallel server
{

    int DEFAULT_PORT = 50000;
    ServerSocket listenSocket;
    Socket clientSockets;
    serverGUI serverGui;
    Dialog78 dialog78;
    ArrayList<Dialog78> clients;
    String line;
    static Integer i;


    public Server78()   // constructor of a TCP server
    {
        try
        {
            listenSocket = new ServerSocket(DEFAULT_PORT);
        } catch (IOException e)    //error
        {
            System.out.println("Problem creating the server-socket");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        System.out.println("Server starts on port " + DEFAULT_PORT);
        serverGui = new serverGUI();
        clients = new ArrayList<>();
        start();
        i = new Integer(0);
    }

    public void run()
    {
        try
        {
            while (true)
            {
                clientSockets = listenSocket.accept();
                dialog78 = new Dialog78(clientSockets, this);
                clients.add(dialog78);
                serverGui.addDialog(dialog78);
                i++;
                System.out.println(i);
                dialog78.bufferSocketOut.println(i.toString());
            }

        } catch (IOException e)
        {
            System.out.println("Problem listening server-socket");
            System.exit(1);
        }

        System.out.println("end of server");
    }
}
