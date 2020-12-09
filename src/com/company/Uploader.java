package com.company;

import java.util.concurrent.CountDownLatch;

class Uploader extends Thread {
    public static int file;
    CountDownLatch cdl;

    public Uploader(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    public void run() {
        try {
            for (int i = 0; i < 25; i++) {
                file = file + 20;
                System.out.print("\uD83D\uDFE9");
                sleep(1000);
            }
            cdl.countDown();
        } catch (Exception ignore) {
        }

    }
}
