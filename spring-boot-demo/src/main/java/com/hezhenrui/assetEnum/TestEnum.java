package com.hezhenrui.assetEnum;

/**
 * @author hzr
 * @date 2022-01-11
 */
public enum TestEnum {
    A("1","2");

    private String a;

    private String b;

    TestEnum(){

    }

    TestEnum(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
