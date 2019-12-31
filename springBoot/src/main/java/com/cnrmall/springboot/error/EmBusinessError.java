package com.cnrmall.springboot.error;



public enum EmBusinessError implements CommonError {
    //通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),


    //20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001,"并未查找到相关数据"),
    USER_PHONE_ISEXIST(20002,"此手机号已参加过报名"),

    //30000开头为投票时的相关错误
    VOTE_ISEXIST(30001,"您已经参与过投票，投票重复"),


    ;

    EmBusinessError(int errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


    private int errCode;
    private String errMsg;


    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
