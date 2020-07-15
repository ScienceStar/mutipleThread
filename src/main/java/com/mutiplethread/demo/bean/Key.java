package com.mutiplethread.demo.bean;

/**
 * @ClassName Key
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/15 18:06
 * @Version V1.0
 **/
public class Key {
    private String keyCode;

    public Key(String keyCode) {
        this.keyCode = keyCode;
    }

    public Key() {
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }
}
