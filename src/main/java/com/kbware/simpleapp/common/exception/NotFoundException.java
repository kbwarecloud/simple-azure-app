package com.kbware.simpleapp.common.exception;

import lombok.NonNull;

public class NotFoundException extends RuntimeException {
    public NotFoundException(@NonNull String resource, long id) {
        super(String.format("%s with id = %s", resource, id));
    }
}
