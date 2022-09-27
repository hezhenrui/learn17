package com.hezhenrui.demo.service.Impl;


import com.hezhenrui.demo.service.Calculator;
import org.springframework.stereotype.Service;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


@Service
public class ForkJoinCalculator1 implements Calculator {

    private ForkJoinPool pool;

    //执行任务RecursiveTask：有返回值  RecursiveAction：无返回值
    private static class SumTask extends RecursiveTask<Long> {
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        //此方法为ForkJoin的核心方法：对任务进行拆分  拆分的好坏决定了效率的高低
        @Override
        protected Long compute() {

            // 当需要计算的数字个数小于6时，直接采用for loop方式计算结果
            if (to - from < 6) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            } else { // 否则，把任务一分为二，递归拆分(注意此处有递归)到底拆分成多少分 需要根据具体情况而定
                int middle = (from + to) / 2;
                SumTask taskLeft = new SumTask(numbers, from, middle);
                SumTask taskRight = new SumTask(numbers, middle + 1, to);
                //这是因为执行compute()方法的线程本身也是一个Worker线程，当对两个子任务调用fork()时，这个Worker线程就会把任务分配给另外两个Worker，
                // 但是它自己却停下来等待不干活了！这样就白白浪费了Fork/Join线程池中的一个Worker线程，导致了4个子任务至少需要7个线程才能并发执行。
//                taskLeft.fork();
//                taskRight.fork();
                invokeAll(taskLeft,taskRight);
                return taskLeft.join() + taskRight.join();
            }
        }
    }

    public ForkJoinCalculator1() {
        // 也可以使用公用的线程池 ForkJoinPool.commonPool()：
        // pool = ForkJoinPool.commonPool()
        pool = new ForkJoinPool();
    }

    @Override
    public long sumUp(long[] numbers) {
        Long result = pool.invoke(new SumTask(numbers, 0, numbers.length - 1));
        pool.shutdown();
        return result;
    }
}
