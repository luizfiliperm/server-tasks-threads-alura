package br.com.alura.server;

import java.io.PrintStream;
import java.util.concurrent.*;

public class CommandC2ReturnFuture implements Callable<Void> {


    private Future<String> futureWS;
    private Future<String> futureDB;
    private PrintStream ps;

    public CommandC2ReturnFuture(Future<String> futureWS, Future<String> futureDB, PrintStream ps) {
        this.futureWS = futureWS;
        this.futureDB = futureDB;
        this.ps = ps;
    }

    @Override
    public Void call() throws Exception {

        System.out.println("Waiting for the commands C2 to finish...");

        String resultWS = null;
        String resultDB = null;
        try {
            resultWS = futureWS.get(15, TimeUnit.SECONDS);
            resultDB = futureDB.get(15, TimeUnit.SECONDS);

            ps.println("Command C2 executed successfully. WS result: " + resultWS + ", DB result: " + resultDB);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("Timeout: cancelling the tasks");
            futureWS.cancel(true);
            futureDB.cancel(true);
            ps.println("Timeout: the tasks were cancelled");

            return null;
        }

        System.out.println("Command C2 finished");

        return null;
    }
}
