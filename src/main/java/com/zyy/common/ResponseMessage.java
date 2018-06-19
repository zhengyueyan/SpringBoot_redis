package com.zyy.common;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 13:57 2018/6/19
 */
public class ResponseMessage {

    private int code;

    private Object result;

    private String message;

    private Object array;

    private Long sumCode;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String massage) {
        this.message = massage;
    }


    public Object getArray() {
        return array;
    }

    public void setArray(Object array) {
        this.array = array;
    }

    public Long getSumCode() {
        return sumCode;
    }

    public void setSumCode(Long sumCode) {
        this.sumCode = sumCode;
    }

    @Override
    public String toString() {
        return "ResponseMessage [code=" + code + ", result=" + result + ", message=" + message + ", array=" + array
                + ", sumCode=" + sumCode + "]";
    }



}
