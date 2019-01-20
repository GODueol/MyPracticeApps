package com.miraclehwan.miraclerecorder.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class MHTextView extends TextView {

    public MHTextView(Context context) {
        super(context);
    }

    public MHTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MHTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTextWithFadeAnim(String text){
        Animation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1000);
        Animation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(1000);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                setText(text);
                startAnimation(fadeIn);
            }
        });
        startAnimation(fadeOut);
    }
}
