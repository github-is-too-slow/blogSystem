package com.billion.common.result;
import java.lang.Enum;
/**
 * 封装响应状态码和状态描述符
 * @author Billion
 * @create 2021/04/04 19:08
 */
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(0, "成功"),
    ERROR(1, "失败"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),


    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),
    USER_REGISTER_ERROR(20006, "用户注册错误"),

    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /* 数据错误：50001-599999 */
    RESULE_DATA_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),

    /* 权限错误：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "无访问权限"),

    /* 文件上传 */
    UPLOAD_ERROR(80001, "上传失败"),

    /* 会话超时 */
    SESSION_TIME_OUT(90001, "Session超时");

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态描述符
     */
    private String msg;

    /**
     * 默认私有构造器
     * @param code
     * @param msg
     */
    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //调用：枚举值.code()
    public Integer code() {
        return this.code;
    }

    //调用：枚举值.message()
    public String msg() {
        return this.msg;
    }

    //调用：枚举类.getCode(枚举名)
    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    //调用：枚举类.getMessage(枚举名)
    public static String getMsg(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.msg;
            }
        }
        return null;
    }
}
