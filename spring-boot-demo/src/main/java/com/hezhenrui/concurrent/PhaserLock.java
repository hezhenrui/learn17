package com.hezhenrui.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * @author hzr
 * @date 2021-12-29
 */
public class PhaserLock {

    private static final int parties = 4;


    public static void main(String[] args) {
        Phaser childrenPhaser = new MyPhaser();
        Phaser phaser = new MyPhaser(childrenPhaser);

        ExecutorService executorService = Executors.newFixedThreadPool(parties);
        for (int i = 0; i < parties; i++) {
            executorService.submit(() -> {
                phaser.register();
                System.out.println("a" + Thread.currentThread().getName());
                phaser.arriveAndAwaitAdvance();
                System.out.println("b" + Thread.currentThread().getName());
                phaser.arriveAndAwaitAdvance();
                System.out.println("c" + Thread.currentThread().getName());
                phaser.arriveAndAwaitAdvance();
                System.out.println("d" + Thread.currentThread().getName());
                phaser.arriveAndAwaitAdvance();
            });
        }
    }

    public static class MyPhaser extends Phaser {

        public MyPhaser() {
        }

        public MyPhaser(Phaser parent) {
            super(parent);
        }

        /**
         * return false结果
         * <p>
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
