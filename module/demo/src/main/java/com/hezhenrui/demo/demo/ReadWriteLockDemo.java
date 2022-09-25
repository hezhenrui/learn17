package com.hezhenrui.demo.demo;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author hzr
 * @date 2021-12-22
 */
public class ReadWriteLockDemo {

    private volatile String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public static void main(String[] args) {

    }

}
