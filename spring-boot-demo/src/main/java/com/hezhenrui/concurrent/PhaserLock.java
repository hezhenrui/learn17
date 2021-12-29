package com.hezhenrui.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * @author hzr
 * @date 2021-12-29
 */
public class PhaserLock {


    public static void main(String[] args) {
        Phaser phaser = new MyPhaser();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 4; i++) {
            phaser.register();
            executorService.submit(() -> {
                System.out.println("a"+ Thread.currentThread().getName());
                phaser.arriveAndAwaitAdvance();
                System.out.println("b"+ Thread.currentThread().getName());
                phaser.arriveAndAwaitAdvance();
                System.out.println("c"+ Thread.currentThread().getName());
                phaser.arriveAndAwaitAdvance();
                System.out.println("d"+ Thread.currentThread().getName());
                phaser.arriveAndAwaitAdvance();
            });
        }
    }

    public static class MyPhaser extends Phaser {

        /**
         * return false结果
         *
         * a0
         * a3
         * a2
         * a1
         * onAdvance阶段1
         * onAdvance阶段2
         * onAdvance阶段3
         * onAdvance阶段4
         */

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("onAdvance" + "阶段1");
                    return false;
                case 1:
                    System.out.println("onAdvance" + "阶段2");
                    return false;
                case 2:
                    System.out.println("onAdvance" + "阶段3");
                    return false;
                case 3:
                    System.out.println("onAdvance" + "阶段4");
                    return false;
                default:
                    return true;
            }
        }
    }
}
