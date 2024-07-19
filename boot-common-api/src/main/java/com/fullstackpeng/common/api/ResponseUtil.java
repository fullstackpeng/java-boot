package com.fullstackpeng.common.api;


import com.fullstackpeng.common.base.R;

public interface ResponseUtil {

    /**
     * 转换
     *
     * @param JSFResponse .
     * @param <T>         .
     * @return .
     */
    static <T> R<T> converter(JR<T> JSFResponse) {
        R<T> result = new R<T>();
        result.setSuccess(JSFResponse.isSuccess());
        result.setCode(JSFResponse.isSuccess() ? R.SC_OK_200 : R.SC_INTERNAL_SERVER_ERROR_500);
        result.setMessage(JSFResponse.getMessage());
        result.setData(JSFResponse.getResult());
        return result;
    }
}
