package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

class Downloader extends Thread{
      private int file=500;
      Semaphore semaphore;
      CountDownLatch cdl ;
    public Downloader(String name, Semaphore semaphore,CountDownLatch cdl) {
        super(name);
        this.semaphore = semaphore;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        try {

            semaphore.acquire();
            cdl.countDown();
            file = file+500;
            System.out.println(this.getName()+"is downloading!");
            for (int i = 0; i <=4; i++) {
                file = file-100;
                sleep(1000);

            }
            semaphore.release();
            cdl.await();
            file=0;
        }catch (Exception ignore){

        }
    }
}
class Uploader extends Thread {
    public static int file;

    @Override
    public void run() {
        try {
            for (int i = 0; i < 25; i++) {
                file = file + 20;
                System.out.print("\uD83D\uDFE9");
                sleep(1000);
            }
            System.out.println();


        } catch (Exception ignore) {

        }
    }

}


public class Main {

    public static void main(String[] args) {
        Uploader uploader = new Uploader();
        uploader.start();
        Semaphore semaphore = new Semaphore(3);
        CountDownLatch cdl = new CountDownLatch(10);
        try {
            uploader.join();
        } catch (Exception ignore) {

        }
        for (int i = 0; i <10; i++) {
            new Downloader("D"+i,semaphore,cdl).start();
        }



    }
}
