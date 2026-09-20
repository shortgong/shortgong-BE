package com.sungjujjang.shortgong.global.exception.exceptions;

import com.sungjujjang.shortgong.global.exception.BusinessException;
import com.sungjujjang.shortgong.global.exception.ErrorCode;

public class ForbiddenRefreshTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new ForbiddenRefreshTokenException();
    public ForbiddenRefreshTokenException() {
        super(ErrorCode.FORBIDDEN_REFRESH_TOKEN);
    }
}
