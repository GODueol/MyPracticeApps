package com.miraclehwan.miraclerecorder.util;

import android.util.Log;

import com.miraclehwan.miraclerecorder.Constant;


/**
 * NLog.class
 * <p>
 * 커스텀 로그 클래스
 */


public class MHLog {
    private static String buildMsg(String msg) {
        StringBuilder buffer = new StringBuilder();

        if (Constant.IS_DEBUG_MODE) {
            final StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];

            buffer.append("[ ");
            buffer.append(Thread.currentThread().getName());
            buffer.append(": ");
            buffer.append(stackTraceElement.getFileName());
            buffer.append(": ");
            buffer.append(stackTraceElement.getLineNumber());
            buffer.append(": ");
            buffer.append(stackTraceElement.getMethodName());
            buffer.append("() ] _____ ");
            buffer.append(msg);
        }

        return buffer.toString();
    }

    public static void v(String msg) {
        if (Constant.IS_DEBUG_MODE && Log.isLoggable(Constant.TAG, Log.VERBOSE)) {
            Log.v(Constant.TAG, buildMsg(msg));
        }
    }

    public static void d(String msg) {
        if (Constant.IS_DEBUG_MODE && Log.isLoggable(Constant.TAG, Log.DEBUG)) {
            Log.d(Constant.TAG, buildMsg(msg));
        }
    }

    public static void i(String msg) {
        if (Constant.IS_DEBUG_MODE && Log.isLoggable(Constant.TAG, Log.INFO)) {
            Log.i(Constant.TAG, buildMsg(msg));
        }
    }

    public static void w(String msg) {
        if (Constant.IS_DEBUG_MODE && Log.isLoggable(Constant.TAG, Log.WARN)) {
            Log.w(Constant.TAG, buildMsg(msg));
        }
    }

    public static void w(String msg, Exception e) {
        if (Constant.IS_DEBUG_MODE && Log.isLoggable(Constant.TAG, Log.WARN)) {
            Log.w(Constant.TAG, buildMsg(msg), e);
        }
    }

    public static void e(String msg) {
        if (Constant.IS_DEBUG_MODE && Log.isLoggable(Constant.TAG, Log.ERROR)) {
            Log.e(Constant.TAG, buildMsg(msg));
        }
    }

    public static void e(String msg, Exception e) {
        if (Constant.IS_DEBUG_MODE && Log.isLoggable(Constant.TAG, Log.ERROR)) {
            Log.e(Constant.TAG, buildMsg(msg), e);
        }
    }
}
