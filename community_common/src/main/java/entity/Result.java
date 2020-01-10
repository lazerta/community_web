package entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {
    private Integer code;
    private boolean flag;
    private String message;
    private T data;

    public static <T> Result<T> create(StatusCode statusCode, boolean flag, T data) {
        return new Result<>(statusCode.getCode(), flag, statusCode.getMessage(), data);
    }

    public static <T> Result<T> create(StatusCode statusCode, boolean flag) {
        return new Result<>(statusCode.getCode(), flag, statusCode.getMessage(), null);
    }

    public static <T> Result<T> create(StatusCode statusCode, String message, boolean flag) {
        return new Result<>(statusCode.getCode(), flag, message, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(StatusCode.Success.getCode(), true, StatusCode.Success.getMessage(), data);
    }

    public static <T> Result<T> success() {
        return success(null);
    }


    public static <T> Result<T> error(StatusCode code, String message) {
        return new Result<T>(code.getCode(), false, message, null);
    }
    public static <T> Result<T> error(StatusCode code) {
        return new Result<T>(code.getCode(), false, code.getMessage(), null);
    }
    public static <T> Result<T> error( String message) {
        return new Result<T>(StatusCode.ERROR.getCode(), false, message, null);
    }
}
