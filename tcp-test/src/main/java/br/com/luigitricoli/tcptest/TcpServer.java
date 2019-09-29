package br.com.luigitricoli.tcptest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;

public class TcpServer {

    public static void start() throws IOException {
        ServerSocket welcomeSocket = new ServerSocket(9789);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            String clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);
            outToClient.writeBytes(clientSentence.toUpperCase() + " at " + Instant.now() + '\n');
        }
    }

}
