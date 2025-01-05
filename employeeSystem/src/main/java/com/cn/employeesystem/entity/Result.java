package com.cn.employeesystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码,1代表成功;0代表失败
    private String msg;//响应信息
    private Object data;//返回的数据

    //成功响应,增删改
    public static Result success(Object data) {
        return new  Result(1, "success",data);

    }
    //失败响应
    public static Result error(String msg) {
        return new  Result(0, msg, null);
    }
}

/*进阶版本*/
//package com.cn.employeesystem.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Result<T> {
//    private Integer code; // 响应码：1 表示成功；0 表示失败
//    private String msg;   // 响应信息
//    private T data;       // 返回的数据
//
//    // 定义常量以避免硬编码
//    public static final int SUCCESS_CODE = 1;
//    public static final int ERROR_CODE = 0;
//
//    /**
//     * 成功响应（带数据）
//     */
//    public static <T> Result<T> success(T data) {
//        return new Result<>(SUCCESS_CODE, "操作成功", data);
//    }
//
//    /**
//     * 成功响应（带数据和自定义消息）
//     */
//    public static <T> Result<T> success(T data, String msg) {
//        return new Result<>(SUCCESS_CODE, msg, data);
//    }
//
//    /**
//     * 成功响应（无数据，仅消息）
//     */
//    public static <T> Result<T> success(String msg) {
//        return new Result<>(SUCCESS_CODE, msg, null);
//    }
//
//    /**
//     * 失败响应（带自定义消息）
//     */
//    public static <T> Result<T> error(String msg) {
//        return new Result<>(ERROR_CODE, msg, null);
//    }
//
//    /**
//     * 失败响应（带自定义响应码和消息）
//     */
//    public static <T> Result<T> error(int code, String msg) {
//        return new Result<>(code, msg, null);
//    }
//}