package br.com.alura.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskServer {

    private ExecutorService threadPool;
    private ServerSocket server;
    private AtomicBoolean running;


    public TaskServer() throws IOException {
        System.out.println("--- Starting server ---");
        this.server = new ServerSocket(9090);
        this.threadPool = Executors.newFixedThreadPool(4, new ThreadFactory() {
            static int num = 1;
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "Thread pool server" + num);
                num++;
                thread.setUncaughtExceptionHandler(new ExceptionTreatment());
                return thread;
            }
        });
        this.running = new AtomicBoolean(true);
    }

    public void run() throws IOException {
        while (this.running.get()){
            try {
                Socket socket = server.accept();
                System.out.println("Accepting new connection from " + socket.getPort());

                DistributeTasks distributedServer = new DistributeTasks(threadPool, socket, this);
                threadPool.execute(distributedServer);
            } catch (SocketException e) {
                System.out.println("SocketException, Is running? " + running);
            }
        }
    }

    public void close() throws IOException {
        running.set(false);
        server.close();
        threadPool.shutdown();
    }

    public static void main(String[] args) throws Exception {
        TaskServer server = new TaskServer();
        server.run();
    }
}