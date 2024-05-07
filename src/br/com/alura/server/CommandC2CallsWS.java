package br.com.alura.server;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class CommandC2CallsWS implements Callable<String> {

    private PrintStream ps;

    public CommandC2CallsWS(PrintStream ps) {
        this.ps = ps;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Running command C2 - Calling WS...");

        ps.println("Processing command C2 - WS");

        Thread.sleep(20000);

        int num = new Random().nextInt(100) + 1;

        ps.println("Command C2 - Calling WS - executed successfully");

        return Integer.toString(num);
    }
}
