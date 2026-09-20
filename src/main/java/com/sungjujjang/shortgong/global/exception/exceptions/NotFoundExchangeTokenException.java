package com.sungjujjang.shortgong.global.exception.exceptions;

import com.sungjujjang.shortgong.global.exception.BusinessException;
import com.sungjujjang.shortgong.global.exception.ErrorCode;

public class NotFoundExchangeTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new NotFoundExchangeTokenException();
    public NotFoundExchangeTokenException() {
        super(ErrorCode.NOT_FOUND_EXCHANGE_TOKEN);
    }
}
