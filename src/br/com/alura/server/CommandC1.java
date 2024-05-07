package br.com.alura.server;

import java.io.PrintStream;

public class CommandC1 implements Runnable {

    private PrintStream ps;

    public CommandC1(PrintStream ps) {
        this.ps = ps;
    }

    @Override
    public void run() {
        System.out.println("Running command C1");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ps.println("Command C1 executed successfully");
    }
}
