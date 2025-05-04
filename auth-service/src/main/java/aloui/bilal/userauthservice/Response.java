package aloui.bilal.userauthservice;

public record Response(String message, ResponseStatus status, int statusCode) {

    public Response(String message, ResponseStatus status) {
        this(message, status, status.getCode());
    }

}
