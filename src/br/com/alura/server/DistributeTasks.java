package br.com.alura.server;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistributeTasks implements Runnable {

    private Socket socket;
    private TaskServer server;

    public DistributeTasks(Socket socket, TaskServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
        System.out.println("Distributing tasks to " + socket);

        Scanner sc = new Scanner(socket.getInputStream());
        PrintStream ps = new PrintStream(socket.getOutputStream());

            while (sc.hasNextLine()) {
                String command = sc.nextLine();
                System.out.println("Command received: " + command);
                switch (command) {
                    case "c1":
                        ps.println("Command 1 received");
                        break;
                    case "c2":
                        ps.println("Command 2 received");
                        break;
                    case "exit":
                        ps.println("Exiting...");
                        server.close();
                        break;
                    default:
                        ps.println("Command not found");
                        break;
                }
            }

            ps.close();
            sc.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
