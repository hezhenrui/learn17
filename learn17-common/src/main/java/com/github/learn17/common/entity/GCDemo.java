package com.github.learn17.common.entity;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author hzr
 * @date 2022-01-12
 */
public class GCDemo implements Serializable {

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

}
