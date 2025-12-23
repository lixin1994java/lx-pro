package com.lxpro.entity;

import java.io.Serializable;

/**
 * 统一接口返回结构。
 *
 * 说明：字段名保持与现有调用一致（例如 {@code meassage} 的拼写）。
 */
public class RestResponseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;
    private int error_code;
    private String meassage;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMeassage() {
        return meassage;
    }

    public void setMeassage(String meassage) {
        this.meassage = meassage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

