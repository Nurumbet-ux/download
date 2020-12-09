package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


public class Main {

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(1);
        CountDownLatch cd = new CountDownLatch(10);
        Semaphore s = new Semaphore(3);
        Uploader uploader = new Uploader(cdl);
        uploader.start();
        try {
            cdl.await();
        } catch (Exception ignore) {}
        System.out.println("\nThe uploading is end!");
        System.out.println("Downloaders are started downloading...");
        for (int i = 1; i <= 10; i++) {
            new Downloader("Passenger "+i,s,cd).start();

        }
        try {
            cd.await();
            System.out.println("Everyone is dowloaded successfully!!!");
            Downloader.file =0;
            System.out.println("file is deleted from server!");

        } catch (Exception ignore) {}


    }
}
