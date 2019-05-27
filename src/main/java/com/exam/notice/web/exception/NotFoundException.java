package com.exam.notice.web.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private String message;

    public NotFoundException(Object id, Class type) {
        this.message = String.format("[%s] for [%s] is not exist in DB. please check one more time.",
                type.getSimpleName(), id.toString());
    }
}
