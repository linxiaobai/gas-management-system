package com.gms.util.error;

/**
 * Created by wangzhen on 2015/2/12.
 * 错误信息接口枚举
 */
public interface ErrorMessage {

    public String getMessage();

    /**
     * 用户错误信息枚举
     */
    public enum UserErrorMessage implements ErrorMessage {

        NOT_THIS_USER("用户名或密码错误");

        private String name;
        private UserErrorMessage(String name) {
            this.name = name;
        }
        public String getMessage(){
            return this.name;
        }
    }

}
