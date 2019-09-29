package br.com.luigitricoli.springrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpClient {

    private final String host;
    Logger logger = LoggerFactory.getLogger(TcpClient.class);

    public TcpClient(String host) {
        this.host = host;
    }

    public String send(String message) throws IOException {
        Socket clientSocket = new Socket(host, 9789);
        clientSocket.setSoTimeout(5000);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        outToServer.writeBytes(message + '\n');
        String modifiedSentence = inFromServer.readLine();
        logger.info("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
        return  modifiedSentence;
    }

}
