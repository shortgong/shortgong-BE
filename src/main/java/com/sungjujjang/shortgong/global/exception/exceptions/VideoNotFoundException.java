package com.sungjujjang.shortgong.global.exception.exceptions;

import com.sungjujjang.shortgong.global.exception.BusinessException;
import com.sungjujjang.shortgong.global.exception.ErrorCode;

public class VideoNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new VideoNotFoundException();
    public VideoNotFoundException() {
        super(ErrorCode.VIDEO_NOT_FOUND);
    }
}
