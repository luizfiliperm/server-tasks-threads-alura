package br.com.alura.client;

import java.io.IOException;
import java.net.Socket;

public class ClientTask {

    public static void main(String[] args) throws IOException {


        Socket socket = new Socket("localhost", 9090);

        System.out.println("Connection established!");

        socket.close();
    }

}
