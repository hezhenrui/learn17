package com.hezhenrui.concurrent;

import java.util.concurrent.*;

public class CCS {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        for (int i=0;i<10;i++){
            threadPoolExecutor.execute(new CountDownLatchTest(countDownLatch));
        }
        countDownLatch.await();
        System.out.println("好了");
    }

    public static class CountDownLatchTest implements Runnable{

        private Integer num;

        private CountDownLatch countDownLatch;

        public CountDownLatchTest(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println("000000");
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 线程Thread-0正在写入数据...
     * 线程Thread-3正在写入数据...
     * 线程Thread-2正在写入数据...
     * 线程Thread-1正在写入数据...
     * 线程Thread-2写入数据完毕，等待其他线程写入完毕
     * 线程Thread-0写入数据完毕，等待其他线程写入完毕
     * 线程Thread-3写入数据完毕，等待其他线程写入完毕
     * 线程Thread-1写入数据完毕，等待其他线程写入完毕
     * 所有线程写入完毕，继续处理其他任务...
     * 所有线程写入完毕，继续处理其他任务...
     * 所有线程写入完毕，继续处理其他任务...
     * 所有线程写入完毕，继续处理其他任务...
     */
    public static class CyclicBarrierTest implements Runnable{

        private CyclicBarrier cyclicBarrier;

        public CyclicBarrierTest(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }

    /**
     * 工人0占用一个机器在生产...
     * 工人1占用一个机器在生产...
     * 工人2占用一个机器在生产...
     * 工人4占用一个机器在生产...
     * 工人5占用一个机器在生产...
     * 工人0释放出机器
     * 工人2释放出机器
     * 工人3占用一个机器在生产...
     * 工人7占用一个机器在生产...
     * 工人4释放出机器
     * 工人5释放出机器
     * 工人1释放出机器
     * 工人6占用一个机器在生产...
     * 工人3释放出机器
     * 工人7释放出机器
     * 工人6释放出机器
     */
    public static class SemaphoreTest implements Runnable{
        private int num;
        private Semaphore semaphore;
        public SemaphoreTest(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
