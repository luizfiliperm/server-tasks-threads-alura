package br.com.alura.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskServer {

    private ExecutorService threadPool;
    private ServerSocket server;
    private boolean running;


    public TaskServer() throws IOException {
        System.out.println("--- Starting server ---");
        this.server = new ServerSocket(9090);
        this.threadPool = Executors.newCachedThreadPool();
        this.running = true;
    }

    public void run() throws IOException {
        while (this.running){
            try {
                Socket socket = server.accept();
                System.out.println("Accepting new connection from " + socket.getPort());

                DistributeTasks distributedServer = new DistributeTasks(socket, this);
                threadPool.execute(distributedServer);
            } catch (SocketException e) {
                System.out.println("SocketException, Is running? " + running);
            }
        }
    }

    public void close() throws IOException {
        running = false;
        server.close();
        threadPool.shutdown();
    }

    public static void main(String[] args) throws Exception {
        TaskServer server = new TaskServer();
        server.run();
    }
}