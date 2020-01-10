package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusCode {
    Success(20000, "Success"),
    ERROR(20001, "failed"),
    UNAUTHORIZED(20002, "not authorized"),
    REMOTE_ERROR(20004, "Remote Error"),
    REPEATED_OPERATION(20005, "repeated operation");



    private int code;
    private String message;
}
