package com.miraclehwan.testopengl.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class MyGLSurfaceView extends GLSurfaceView {

    private MyRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        //OpenGL ES 버전 정의, setRenderer 호출 전에 정의해야함
        setEGLContextClientVersion(2);
        mRenderer = new MyRenderer();
        setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}
