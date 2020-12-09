package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

class Downloader extends Thread {
    public static int file = 500;
    Semaphore semaphore;
    CountDownLatch cd;

    public Downloader(String name, Semaphore semaphore, CountDownLatch cd) {
        super(name);
        this.semaphore = semaphore;
        this.cd = cd;
    }

    public void run() {
        try {
            semaphore.acquire();
            file = file + 500;
            System.out.println(this.getName() + "is downloading!");
            for (int i = 0; i <= 4; i++) {
                file = file - 100;
                sleep(1000);
            }
            semaphore.release();
            cd.countDown();
        } catch (Exception ignore) {

        }
    }
}
