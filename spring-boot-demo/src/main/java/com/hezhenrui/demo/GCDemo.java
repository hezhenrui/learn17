package com.hezhenrui.demo;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author hzr
 * @date 2022-01-12
 */
public class GCDemo {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GCDemo.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .toString();
    }

    public static void main(String[] args) {
        GCDemo gcDemo = new GCDemo();
        gcDemo.setId("1");
        gcDemo.setName("2");
        System.gc();
        System.out.println(gcDemo);
    }
}
