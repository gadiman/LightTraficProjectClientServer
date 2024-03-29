package Server;

//file name: Dialog78.java
//Iyar 5770 update Sivan 5778
//Levian Yehonatan

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

class Dialog78 extends Thread // parallel dialogs on the same socket
{

    Socket client;
    Server myServer;
    BufferedReader bufferSocketIn;
    PrintWriter bufferSocketOut;
    DialogWin78 myOutput;
    public List<Socket> Sockets;
    int i =0;

    String name,carNum,LTnum,CRnum;



    public Dialog78(Socket clientSocket, Server myServer)
    {
        client = clientSocket;
        this.myServer = myServer;
        try
        {
            //Init streams to read/write text in this socket
            bufferSocketIn = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            bufferSocketOut = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    clientSocket.getOutputStream())), true);
        } catch (IOException e)
        {
            try
            {
                client.close();
            } catch (IOException e2)
            {
            }
            System.err.println("server:Exception when opening sockets: " + e);
            return;
        }
       // myOutput = new DialogWin78("Dialog Win for: " + client.toString(), this);
        start();
    }

    public void run()
    {
        String line;
        boolean stop=false;
        try
        {
            while (true)
            {
                line = bufferSocketIn.readLine();
                if (line == null)
                    break;
                else if (line.equals("end"))
                    break;

                String[] split = line.split("\\s+");
                carNum = split[0];
                LTnum = split[1];
                CRnum = split[2];
                name = split[3];
                myServer.sendCar(new DataTransfer(carNum,LTnum,CRnum,name));


            }
        } catch (IOException e)
        {
        } finally
        {
            try
            {
                client.close();
            } catch (IOException e2)
            {
            }
        }

        //myOutput.printMe("end of  dialog ");
        //myOutput.send.setText("Close");

    }

    void exit()
    {
        try
        {
            client.close();
        } catch (IOException e2)
        {
        }
    }



}