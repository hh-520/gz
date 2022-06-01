package com.yskj.robot.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Result<T> {

    private int status; // 状态0：接口调用成功
    private T data;
    private String msg; //提示信息

    private Result() {
    }

    private Result(int status) {
        this.status = status;
    }

    private Result(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private Result(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.status == 0;
    }

    public static Result success(){
        return new Result(0);
    }
    public static <T> Result success(T data){
        return new Result(0,data);
    }
    public static <T> Result success(T data,String msg){
        return new Result(0,data,msg);
    }
    public static  Result fail(int status){
        return new Result(status);
    }
    public  static Result fail(int status,String msg){
        return new Result(status, null,msg);
    }


}
