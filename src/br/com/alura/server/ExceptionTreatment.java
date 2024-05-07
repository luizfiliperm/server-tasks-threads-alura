package br.com.alura.server;

public class ExceptionTreatment implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("An exception occurred in thread " + t.getName() + ", " + e.getMessage());
    }
}
