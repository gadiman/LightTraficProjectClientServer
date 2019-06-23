package Client;

import java.io.*;
import java.net.*;

import LightTraffic.Event64;

/**
 * It represent a client.
 *
 * @author Arie and Gad.
 */
public class Client extends Thread {
    private String SERVER_HOST = "Localhost";
    private int DEFAULT_PORT = 50000;
    private Socket clientSocket = null;
    private BufferedReader bufferSocketIn;
    private PrintWriter bufferSocketOut;
//    BufferedReader keyBoard;
//    ClientWin78 myOutput;
    private String line;
    private Event64 evConnectClient;

    public Client(Event64 evClientConn) {
        this.evConnectClient = evClientConn;
        start();
    }

    public void run() {
        try {
            // Request from the server:
            clientSocket = new Socket(SERVER_HOST, DEFAULT_PORT);
            // Init streams to read/write text in this socket:
            bufferSocketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            bufferSocketOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

//  	   Init streams to read text from the keyboard
//	   keyBoard = new BufferedReader(
//	   new InputStreamReader(System.in));


           // myOutput = new ClientWin78("Client  ", this);

            // notice about the connection
          //  myOutput.printMe("Connected to " + clientSocket.getInetAddress() +
             //       ":" + clientSocket.getPort());
            while (true) {
                line = bufferSocketIn.readLine(); // Reads a line from the server.
                if (line == null) { // Check if the connection is closed.
//                    myOutput.printMe("Connection closed by the Server.");
                    break;
                }
//                myOutput.printOther(line); // shows it on the screen
                if (line.equals("end"))
                {
                    break;
                }
                evConnectClient.sendEvent(line);
            }
        } catch (IOException e) {
//            myOutput.printMe(e.toString());
            System.err.println(e);
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e2) { }
        }
//        myOutput.printMe("end of client ");
//        myOutput.send.setText("Close");

        System.out.println("End of client.");
    }

    public static void main(String[] args) {
        Event64 evControlClient = new Event64();
        Client client = new Client(evControlClient);
        new LightTraffic.BuildTrafficLight(evControlClient);
    }
}
