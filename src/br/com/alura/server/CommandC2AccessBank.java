package br.com.alura.server;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class CommandC2AccessBank implements Callable<String> {

    private PrintStream ps;

    public CommandC2AccessBank(PrintStream ps) {
        this.ps = ps;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Running command C2- Accessing bank...");

        ps.println("Processing command C2 - DB");

        Thread.sleep(35000);

        int num = new Random().nextInt(100) + 1;

        ps.println("Command C2 - Accessing bank - executed successfully");

        return Integer.toString(num);
    }
}
