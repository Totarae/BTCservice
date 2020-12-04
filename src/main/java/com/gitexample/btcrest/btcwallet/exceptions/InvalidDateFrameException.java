package com.gitexample.btcrest.btcwallet.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidDateFrameException extends RuntimeException {

    private String msg;

    // Init error message
    public InvalidDateFrameException(String msg) {
        this.msg = msg;
    }
}
