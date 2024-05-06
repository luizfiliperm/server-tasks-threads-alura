package br.com.alura.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DistributeTasks implements Runnable {

    private Socket socket;

    public DistributeTasks(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
        System.out.println("Distributing tasks to " + socket);

        Scanner sc = new Scanner(socket.getInputStream());

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            System.out.println("Received command: " + command);
        }


        sc.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
