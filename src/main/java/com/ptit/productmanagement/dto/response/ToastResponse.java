package com.ptit.productmanagement.dto.response;

public class ToastResponse {
    private final boolean deleted;
    private final String message;

    public ToastResponse(boolean deleted, String message) {
        this.deleted = deleted;
        this.message = message;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public String getMessage() {
        return message;
    }
}
