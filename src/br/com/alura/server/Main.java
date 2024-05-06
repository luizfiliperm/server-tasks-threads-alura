package br.com.alura.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("--- Starting server ---");
        ServerSocket server = new ServerSocket(9090);

        // ExecutorService threadPool = Executors.newFixedThreadPool(2);
        ExecutorService threadPool = Executors.newCachedThreadPool();

        while (true){
            Socket socket = server.accept();
            System.out.println("Accepting new connection from " + socket.getPort());

            DistributeTasks distributedServer = new DistributeTasks(socket);
            threadPool.execute(distributedServer);
        }
    }
}