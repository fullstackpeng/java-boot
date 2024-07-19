package com.fullstackpeng.common.web.exception;

import cn.hutool.core.util.ObjectUtil;
import com.fullstackpeng.common.base.R;
import com.fullstackpeng.common.base.exception.OssException;
import com.fullstackpeng.common.base.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public R<Void> handleServiceException(ServiceException e) {
        log.error(e.getMessage(), e);
        Integer code = e.getCode();
        return ObjectUtil.isNotNull(code) ? R.NG(code, e.getMessage()) : R.NG(e.getMessage());
    }

    /**
     * oss异常
     */
    @ExceptionHandler(OssException.class)
    public R<Void> handleOssException(OssException e) {
        log.error("oss异常：{}", e.getMessage(), e);
        return R.NG(e.getMessage());
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                       HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod(), e);
        return R.NG(e.getMessage());
    }

    /**
     * 处理参数类型不匹配异常
     *
     * @param e 异常
     * @return .
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R<Void> methodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e,
            HttpServletRequest request) {
        log.error("请求地址：{}，参数类型不匹配，异常信息：{}", request.getRequestURI(), e.getMessage(), e);
        return R.NG(
                String.format("参数类型不匹配，参数%s的值%s不合法", e.getName(), e.getValue())
        );
    }

    /**
     * 处理运行时异常
     *
     * @param e 异常
     * @return .
     */
    @ExceptionHandler(RuntimeException.class)
    public R<Void> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.error("请求地址：{}，异常信息：{} ", request.getRequestURI(), e.getMessage(), e);
        return R.NG(e.getMessage());
    }

    /**
     * 处理异常
     *
     * @param e 异常
     * @return .
     */
    @ExceptionHandler(Exception.class)
    public R<Void> handleException(Exception e, HttpServletRequest request) {
        log.error("请求地址：{}，异常信息：{}", request.getRequestURI(), e.getMessage(), e);
        return R.NG(e.getMessage());
    }

    /**
     * 处理参数校验异常
     *
     * @param e 异常
     * @return .
     */
    @ExceptionHandler(BindException.class)
    public R<Void> handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        List<ObjectError> allErrors = e.getAllErrors();
        StringJoiner joiner = new StringJoiner(",");
        allErrors.forEach(objectError -> joiner.add(objectError.getDefaultMessage()));
        joiner.add(e.getMessage());
        return R.NG(joiner.toString());
    }

    /**
     * 处理参数校验异常
     *
     * @param e 异常
     * @return .
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R<Void> constraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        StringJoiner joiner = new StringJoiner(",");
        e.getConstraintViolations().forEach(constraintViolation -> joiner.add(constraintViolation.getMessage()));
        return R.NG(joiner.toString());
    }

    /**
     * 处理参数校验异常
     *
     * @param e 异常
     * @return .
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return R.NG(message);
    }
}
