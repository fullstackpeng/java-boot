package com.fullstackpeng.common.base.context;

/**
 * 租户上下文
 */
public class TenantContext {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(String tenant) {
        THREAD_LOCAL.set(tenant);
    }

    public static String get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

}
