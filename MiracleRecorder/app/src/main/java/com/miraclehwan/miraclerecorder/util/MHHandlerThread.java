package com.miraclehwan.miraclerecorder.util;


import android.os.Handler;
import android.os.HandlerThread;

public class MHHandlerThread extends HandlerThread {

    private Handler handler;
    public Handler getHandler() {
        if (handler == null){
            handler = new Handler(getLooper());
        }
        return handler;
    }

    @Override
    public boolean quit() {
        if (handler != null){
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        return super.quit();
    }

    public MHHandlerThread(String name) {
        super(name);
        start();
        handler = new Handler(getLooper());
    }

    public MHHandlerThread(String name, int priority) {
        super(name, priority);
    }
}
