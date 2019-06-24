package com.miraclehwan.testopengl.opengl;

import android.opengl.GLES20;

/**
 * 버텍스쉐이더 - 정점을 이용한 위치 및 변형에 관여
 * 프레그먼트 쉐이더 - 면을 채우는 역할
 */

public class Shader {

    public static final String TRIANGLE_VERTEX_SHADER_CODE =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "gl_Position = vPosition;" +
                    "}";

    /**
     * precision mediump - 정확도를 중간 ( Highp : 최고 , lowp : 최하 )
     */
    public static final String TRIANGLE_FRAGMENT_SHADER_CODE =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main(){" +
                    "gl_FragColor = vColor;" +
                    "}";
}
