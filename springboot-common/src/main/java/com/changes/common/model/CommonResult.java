package com.changes.common.model;

/**
 * @author LiuJunJie
 * @since 2019/11/20 17:47
 */
public class CommonResult<T> {

    private long code;

    private String message;

    private T data;

    public CommonResult(){}

    public CommonResult(long code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }


    public static <T> CommonResult<T> success(String message,T data){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),message,data);
    }


    public static <T> CommonResult<T> failed(IErrorCode iErrorCode){
        return new CommonResult<T>(iErrorCode.getCode(),iErrorCode.getMessage(),null);
    }

    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<T>(ResultCode.FAILED.getCode(),message,null);
    }

    public static <T> CommonResult<T> validateFailed(){
        return  failed(ResultCode.VALIDATE_FAILED);
    }

    public static <T> CommonResult<T> validateFailed(String message){
        return failed(message);
    }


    public static <T> CommonResult<T> unauthorized(T data){
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMessage(),data);
    }

    public static <T> CommonResult<T> forbidden(T data){
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getMessage(),data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
