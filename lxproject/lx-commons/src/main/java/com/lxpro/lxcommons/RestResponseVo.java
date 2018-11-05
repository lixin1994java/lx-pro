package com.lxpro.lxcommons;

/**
 * rest 接口返回对象
 */
public class RestResponseVo {

    private  Boolean success;
    private  Integer error_code;
    private  String  error_message;
    private  String  meassage;
    private  Object data;

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
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
