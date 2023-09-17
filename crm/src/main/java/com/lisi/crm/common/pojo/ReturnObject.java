package com.lisi.crm.common.pojo;

public class ReturnObject {
    private String successCode;  //是否成功
    private String message;   //失败的相应信息

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
