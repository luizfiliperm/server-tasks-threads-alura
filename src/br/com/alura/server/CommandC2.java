package br.com.alura.server;

import java.io.PrintStream;

public class CommandC2 implements Runnable {

    private PrintStream ps;

    public CommandC2(PrintStream ps) {
        this.ps = ps;
    }

    @Override
    public void run() {
        System.out.println("Running command C2");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ps.println("Command C2 executed successfully");
    }
}
