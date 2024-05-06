package br.com.alura.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientTask {

    public static void main(String[] args) throws IOException {


        Socket socket = new Socket("localhost", 9090);

        System.out.println("Connection established!");

        Scanner sc = new Scanner(System.in);

        sc.nextLine();

        socket.close();
    }

}
