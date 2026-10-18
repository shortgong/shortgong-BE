package com.sungjujjang.shortgong.global.exception.exceptions;

import com.sungjujjang.shortgong.global.exception.BusinessException;
import com.sungjujjang.shortgong.global.exception.ErrorCode;

public class ForbiddenVideoException extends BusinessException {
    public static final BusinessException EXCEPTION = new ForbiddenVideoException();
    public ForbiddenVideoException() {
        super(ErrorCode.FORBIDDEN_VIDEO);
    }
}
