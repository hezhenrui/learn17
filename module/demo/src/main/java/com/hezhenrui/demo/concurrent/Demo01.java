package com.hezhenrui.demo.concurrent;

import cn.hutool.core.convert.Convert;
import com.hezhenrui.demo.domain.Person;
import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author hzr
 * @date 2022-04-06
 */
public class Demo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        System.out.println("Hello World!");
//        // 构造一个10000个元素的集合
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 10000; i++) {
//            list.add(i);
//        }
//        // 统计并行执行list的线程
//        Set<Thread> threadSet = new CopyOnWriteArraySet<>();
//        // 并行执行
//        list.parallelStream().forEach(integer -> {
//            Thread thread = Thread.currentThread();
//            // System.out.println(thread);
//            // 统计并行执行list的线程
//            threadSet.add(thread);
//        });
//        System.out.println("threadSet一共有" + threadSet.size() + "个线程");
//        System.out.println("系统一个有"+Runtime.getRuntime().availableProcessors()+"个cpu");
//        List<Integer> list1 = new ArrayList<>();
//        List<Integer> list2 = new ArrayList<>();
//        for (int i = 0; i < 100000; i++) {
//            list1.add(i);
//            list2.add(i);
//        }
//        Set<Thread> threadSetTwo = new CopyOnWriteArraySet<>();
//        CountDownLatch countDownLatch = new CountDownLatch(2);
//        Thread threadA = new Thread(() -> {
//            list1.parallelStream().forEach(integer -> {
//                Thread thread = Thread.currentThread();
//                // System.out.println("list1" + thread);
//                threadSetTwo.add(thread);
//            });
//            countDownLatch.countDown();
//        });
//        Thread threadB = new Thread(() -> {
//            list2.parallelStream().forEach(integer -> {
//                Thread thread = Thread.currentThread();
//                // System.out.println("list2" + thread);
//                threadSetTwo.add(thread);
//            });
//            countDownLatch.countDown();
//        });
//
//        threadA.start();
//        threadB.start();
//        countDownLatch.await();
//        System.out.print("threadSetTwo一共有" + threadSetTwo.size() + "个线程");
//
//        System.out.println("---------------------------");
//        System.out.println(threadSet);
//        System.out.println(threadSetTwo);
//        System.out.println("---------------------------");
//        threadSetTwo.addAll(threadSet);
//        System.out.println(threadSetTwo);
//        System.out.println("threadSetTwo一共有" + threadSetTwo.size() + "个线程");
//        System.out.println("系统一个有"+Runtime.getRuntime().availableProcessors()+"个cpu");
        List<Person> personList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setId(i);
            personList.add(person);
        }

        long begin = System.currentTimeMillis();
        // 自定义一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 循环创建10个CompletableFuture
        List<CompletableFuture<Integer>> collect = IntStream.range(1, 10).mapToObj(i -> CompletableFuture.supplyAsync(() -> {
                    // 在i=5的时候抛出一个NPE
                    if (i == 5) {
                        throw new NullPointerException();
                    }
                    try {
                        // 每个依次睡眠1-9s，模拟线程耗时
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                    return i;
                }, executorService)
                // 这里处理一下i=5时出现的NPE
                // 如果这里不处理异常，那么异常会在所有任务完成后抛出,小伙伴可自行测试
                .exceptionally(Error -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 100;
                })).collect(Collectors.toList());
        // List列表转成CompletableFuture的Array数组,使其可以作为allOf()的参数
        // 使用join()方法使得主线程阻塞，并等待所有并行线程完成
        CompletableFuture<Void> result = CompletableFuture.allOf(collect.toArray(new CompletableFuture[]{}));
        if (result.isDone()) {
            collect.parallelStream().forEach(integerCompletableFuture -> {
                try {
                    System.out.println(integerCompletableFuture.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }


        List<Person> personList1 = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Person person = new Person();
            person.setId(i);
            personList1.add(person);
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<Person> list = forkJoinPool.invoke(new RunTask(0, 1000, personList1));
        System.out.println(1);
    }

    public static class RunTask extends RecursiveTask<List<Person>> {

        private int start;

        private int end;

        private List<Person> personList;

        public RunTask(int start, int end, List<Person> personList) {
            this.start = start;
            this.end = end;
            this.personList = personList;
        }

        @SneakyThrows
        @Override
        protected List<Person> compute() {
            if (end - start < 100) {
                List<Person> list = personList.subList(start, end);
                if (end < 1000) {
                    list.add(personList.get(end + 1));
                }
                List<Person> list1 = new CopyOnWriteArrayList<>();
                for (Person person : list) {
                    Person person1 = new Person();
                    person1.setName(Convert.toStr(person.getId()));
                    person1.setSalary(Convert.toStr(person.getId() * 10));
                    list1.add(person1);
                }
                return list1;
            } else {
                int middle = (start + end) / 2;
                RunTask runTask1 = new RunTask(start, middle, personList);
                RunTask runTask2 = new RunTask(middle + 1, end, personList);
                invokeAll(runTask1, runTask2);
                runTask1.get().addAll(runTask2.get());
                return runTask1.get();
            }
        }
    }
}
