package com.xxy.rpc.common;

/**
 * @Author: XXY
 * @Date: 2020/7/14 9:00
 */
public class CommonResponse<R> {
    private final boolean success;
    private final R result;
    private final Throwable exception;

    private CommonResponse(R result) {
        this(result, true, null);
    }

    private CommonResponse(R result, boolean success, Throwable exception) {
        this.success = success;
        this.result = result;
        this.exception = exception;
    }

    /**
     * Construct a successful response with given object.
     *
     * @param result result object
     * @param <T>    type of the result
     * @return constructed server response
     */
    public static <T> CommonResponse<T> ofSuccess(T result) {
        return new CommonResponse<T>(result);
    }

    /**
     * Construct a failed response with given exception.
     *
     * @param ex cause of the failure
     * @return constructed server response
     */
    public static <T> CommonResponse<T> ofFailure(Throwable ex) {
        return new CommonResponse<T>(null, false, ex);
    }

    /**
     * Construct a failed response with given exception.
     *
     * @param ex     cause of the failure
     * @param result additional message of the failure
     * @return constructed server response
     */
    public static <T> CommonResponse<T> ofFailure(Throwable ex, T result) {
        return new CommonResponse<T>(result, false, ex);
    }

    public boolean isSuccess() {
        return success;
    }

    public R getResult() {
        return result;
    }

    public Throwable getException() {
        return exception;
    }
}
