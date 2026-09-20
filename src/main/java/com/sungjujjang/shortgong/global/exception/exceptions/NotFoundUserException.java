package com.sungjujjang.shortgong.global.exception.exceptions;

import com.sungjujjang.shortgong.global.exception.BusinessException;
import com.sungjujjang.shortgong.global.exception.ErrorCode;

public class NotFoundUserException extends BusinessException {
    public static final BusinessException EXCEPTION = new NotFoundUserException();
    public NotFoundUserException() {
        super(ErrorCode.NOT_FOUND_USER);
    }
}
