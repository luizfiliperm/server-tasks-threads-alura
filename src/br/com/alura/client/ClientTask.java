package br.com.alura.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTask {

    public static void main(String[] args) throws Exception {


        Socket socket = new Socket("localhost", 9090);

        System.out.println("Connection established!");


        Thread sendCommand = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Type a command: ");
                    PrintStream ps = new PrintStream(socket.getOutputStream());
                    Scanner sc = new Scanner(System.in);

                    while(sc.hasNextLine()){
                        String line = sc.nextLine();

                        if(line.trim().isEmpty()){
                            break;
                        }
                        ps.println(line);
                    }
                    ps.close();
                    sc.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        Thread receiveCommand = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner sc = new Scanner(socket.getInputStream());

                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        System.out.println(line);
                    }
                    sc.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        sendCommand.start();
        receiveCommand.start();
        receiveCommand.join();

        System.out.println("Closing client socket");
        socket.close();
    }

}
