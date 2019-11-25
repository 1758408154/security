package com.changes.common.model;

/**
 * @author LiuJunJie
 * @since 2019/11/20 17:44
 */
public enum ResultCode implements IErrorCode {

    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    PASSWORD(500,"密码错误！");

    private long code;

    private String message;

    private ResultCode(long code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }}
