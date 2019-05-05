package com.miraclehwan.miraclerecorder.util;

import android.animation.Animator;

public class MHAnimatorListenerWrapper implements Animator.AnimatorListener {

    private onStart mOnStart;
    private onEnd mOnEnd;
    private onCancel mOnCancel;
    private onRepeat mOnRepeat;

    @FunctionalInterface
    public interface onStart{
        void onCallback(Animator animator);
    }

    @FunctionalInterface
    public interface onEnd{
        void onCallback(Animator animator);
    }

    @FunctionalInterface
    public interface onCancel{
        void onCallback(Animator animator);
    }

    @FunctionalInterface
    public interface onRepeat{
        void onCallback(Animator animator);
    }

    public MHAnimatorListenerWrapper() { }

    public MHAnimatorListenerWrapper setAnimationStartCallback(onStart onStart){
        this.mOnStart = onStart;
        return this;
    }

    public MHAnimatorListenerWrapper setAnimationEndCallback(onEnd onEnd){
        this.mOnEnd = onEnd;
        return this;
    }

    public MHAnimatorListenerWrapper setAnimationCancelCallback(onCancel onCancel){
        this.mOnCancel = onCancel;
        return this;
    }

    public MHAnimatorListenerWrapper setAnimationRepeatCallback(onRepeat onRepeat){
        this.mOnRepeat = onRepeat;
        return this;
    }

    @Override
    public void onAnimationStart(Animator animation) {
        if (mOnStart != null){
            mOnStart.onCallback(animation);
        }
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (mOnEnd != null){
            mOnEnd.onCallback(animation);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        if (mOnCancel != null){
            mOnCancel.onCallback(animation);
        }
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        if (mOnRepeat != null){
            mOnRepeat.onCallback(animation);
        }
    }
}
