package aloui.bilal.userauthservice;

public enum ResponseStatus {
    SUCCESS(1000, "Success"),
    ERROR(1001, "Error"),
    USER_ALREADY_EXISTS(1002, "User already exists"),
    INVALID_CREDENTIALS(1003, "Invalid credentials"),
    USER_NOT_FOUND(1004, "User not found"),
    UNAUTHORIZED(1005, "Unauthorized"),
    FORBIDDEN(1006, "Forbidden"),
    SERVER_ERROR(1007, "Server error"),
    VALIDATION_ERROR(1008, "Validation error"),
    BAD_REQUEST(1009, "Bad request");

    private final int code;
    private final String message;

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}