package com.billion.common.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装调用api的返回值
 * @author Billion
 * @create 2021/04/04 19:08
 */
@NoArgsConstructor
@Data
public class Result implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private Object data;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(ResultCode resultCode) {
        setResultCode(resultCode);
    }

    public void setResultCode(ResultCode resultCode){
        this.code = resultCode.code();
        this.msg = resultCode.msg();
    }
    //定义返回数据类型为map
    public Map<String, Object> simple(){
        this.data = new HashMap<String, Object>();
        return (Map<String, Object>) this.data;
    }

    public static Result success(){
        return new Result(ResultCode.SUCCESS);
    }

    public static Result success(Object data){
        Result result = success();
        result.setData(data);
        return result;
    }

    public static Result error(ResultCode resultCode){
        return new Result(resultCode);
    }

    public static Result error(ResultCode resultCode, Object data){
        Result result = error(resultCode);
        result.setData(data);
        return result;
    }
}
