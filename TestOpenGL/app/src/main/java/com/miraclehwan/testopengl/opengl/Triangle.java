package com.miraclehwan.testopengl.opengl;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Triangle {

    private MyRenderer mRenderer;
    //정점 버퍼를 설정
    private FloatBuffer vertexBuffer;
    //꼭지점당 좌표수는 x,y,z 3개
    final static int COORDS_PER_VERTEX = 3;
    //삼각형의 좌표로 x,y,z축 3개의 꼭지점을 설정함
    final static float triangleCoords[] = {
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    };

    //색상을 관리
    private float color[] = {1.0f, 1.0f, 0f, 1.0f};

    private int mProgram;
    private int mPositionHandle;
    private int mColorHandle;

    //꼭지점 갯수는 전체길이에서 꼭지점의 좌표수로 나눈값임
    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;

    //4bytes per vertex
    private final int vertexStride = COORDS_PER_VERTEX * 4;

    public Triangle(MyRenderer mRenderer) {
        this.mRenderer = mRenderer;

        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);

        int vertexShader = mRenderer.loadShader(GLES20.GL_VERTEX_SHADER, Shader.TRIANGLE_VERTEX_SHADER_CODE);
        int fragmentShader = mRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, Shader.TRIANGLE_FRAGMENT_SHADER_CODE);

        mProgram = GLES20.glCreateProgram();

        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);
    }

    public void draw(){
        GLES20.glUseProgram(mProgram);

        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
