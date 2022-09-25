package com.hezhenrui.demo.concurrent;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();

        Thread putThread = new Thread(() -> {
            System.out.println("put thread start");
            try {
                System.out.println("第一次放");
                queue.put(1);
                System.out.println("第二次放");
                queue.put(2);
            } catch (InterruptedException e) {
            }
            System.out.println("put thread end");
        });

        Thread takeThread = new Thread(() -> {
            System.out.println("take thread start");
            try {
                System.out.println("take from putThread: " + queue.take());
                System.out.println("take from putThread: " + queue.take());
            } catch (InterruptedException e) {
            }
            System.out.println("take thread end");
        });

        putThread.start();
        Thread.sleep(1000);
        takeThread.start();
    }
}
