package com.gms.util;

/**
 * Created by Kevin on 2015/4/17.
 */
public enum JsonMsgObj {
    LOGIN_INFO(1, "登录信息传输对象"), NO(0, "否");

    private int id;

    private String name;

    private JsonMsgObj(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static JsonMsgObj idOf(int id) {
        for (JsonMsgObj item : values()) {
            if (item.id == id) {
                return item;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
