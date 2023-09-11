package com.lisi.crm.common.pojo;

public class ReturnObject {
    private String loginSuccessCode;  //登陆是否成功
    private String loginMessage;   //登陆失败的相应信息

    public String getLoginSuccessCode() {
        return loginSuccessCode;
    }

    public void setLoginSuccessCode(String loginSuccessCode) {
        this.loginSuccessCode = loginSuccessCode;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }
}
