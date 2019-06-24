package com.miraclehwan.testopengl.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 각각의 메서드에서 파라미터로 전달되는 GL10 객체는
 * OpenGL ES 1.0, 1.1 버전에서 사용되며 2.0 에서는 사용하지 않음
 * -> 다만 이전 버전의 호환을 위해 사용되고 있음
 */

public class MyRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle;

    /**
     * GLSurfaceView가 생성될 경우 해당 메서드 호출
     *
     * @param gl
     * @param config
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mTriangle = new Triangle(this);
    }

    /**
     * GLSurfaceView가 변경될 경우 호출
     *
     * @param gl
     * @param width  - 디바이스의 폭
     * @param height - 디바이스의 넓이
     */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    /**
     * @param gl
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        //배경색
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
        mTriangle.draw();
    }

    public static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
