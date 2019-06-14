package com.miraclehwan.miraclegithub.util

import android.util.Log

import com.miraclehwan.miraclerx.Constants.IS_DEBUG
import com.miraclehwan.miraclerx.Constants.TAG

object Log {
    private fun buildMsg(msg: String): String {
        val buffer = StringBuilder()

        if (IS_DEBUG) {
            val stackTraceElement = Thread.currentThread().stackTrace[4]
            buffer.apply {
                append("[ ")
                append(Thread.currentThread().name)
                append(": ")
                append(stackTraceElement.fileName)
                append(": ")
                append(stackTraceElement.lineNumber)
                append(": ")
                append(stackTraceElement.methodName)
                append("() ] _____ ")
                append(msg)
            }
        }

        return buffer.toString()
    }

    fun v(msg: String) {
        if (IS_DEBUG && Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(TAG, buildMsg(msg))
        }
    }

    fun d(msg: String) {
        if (IS_DEBUG && Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, buildMsg(msg))
        }
    }

    fun i(msg: String) {
        if (IS_DEBUG && Log.isLoggable(TAG, Log.INFO)) {
            Log.i(TAG, buildMsg(msg))
        }
    }

    fun w(msg: String) {
        if (IS_DEBUG && Log.isLoggable(TAG, Log.WARN)) {
            Log.w(TAG, buildMsg(msg))
        }
    }

    fun w(msg: String, e: Exception) {
        if (IS_DEBUG && Log.isLoggable(TAG, Log.WARN)) {
            Log.w(TAG, buildMsg(msg), e)
        }
    }

    fun e(msg: String) {
        if (IS_DEBUG && Log.isLoggable(TAG, Log.ERROR)) {
            Log.e(TAG, buildMsg(msg))
        }
    }

    fun e(msg: String, e: Exception) {
        if (IS_DEBUG && Log.isLoggable(TAG, Log.ERROR)) {
            Log.e(TAG, buildMsg(msg), e)
        }
    }
}
