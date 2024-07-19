package com.fullstackpeng.common.base.exception;

/**
 * oss异常
 */
public class OssException extends RuntimeException {
    public OssException() {
        super();
    }

    public OssException(String message) {
        super(message);
    }

    public OssException(String message, Throwable cause) {
        super(message, cause);
    }
}
