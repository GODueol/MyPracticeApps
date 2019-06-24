package com.miraclehwan.testopengl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.miraclehwan.testopengl.opengl.MyGLSurfaceView

class MainActivity : AppCompatActivity() {

    val mMyGLSurfaceView : MyGLSurfaceView by lazy{ MyGLSurfaceView(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(mMyGLSurfaceView)
    }
}
