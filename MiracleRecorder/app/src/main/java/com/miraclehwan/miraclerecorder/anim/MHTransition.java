package com.miraclehwan.miraclerecorder.anim;

import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;

public class MHTransition {

    public static final int AUTO = 101;
    public static final int BOUND = 102;


    @FunctionalInterface
    public interface IStartTransition {
        void onCallback();
    }

    @FunctionalInterface
    public interface IEndTransition {
        void onCallback();
    }

    private Transition mTransition;
    private IStartTransition mIStartTransition;
    private IEndTransition mIEndTransition;

    public MHTransition(int type) {
        switch (type){
            case AUTO:
                mTransition = new AutoTransition();
                mTransition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
                break;
            case BOUND:
                mTransition = new ChangeBounds();
                mTransition.setInterpolator(new AccelerateDecelerateInterpolator());
                break;
            default:
                mTransition = new AutoTransition();
                mTransition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
                break;
        }
    }

    public MHTransition setDuration(long duration){
        if (mTransition != null){
            mTransition.setDuration(duration);
        }
        return this;
    }

    public MHTransition setStartCallback(IStartTransition IStartTransition){
        if (mTransition != null){
            mIStartTransition = IStartTransition;
        }
        return this;
    }

    public MHTransition setEndCallback(IEndTransition IEndTransition){
        if (mTransition != null){
            mIEndTransition = IEndTransition;
        }
        return this;
    }

    public Transition getTransition(){
        if (mTransition != null){
            mTransition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    if (mIStartTransition != null){
                        mIStartTransition.onCallback();
                    }
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    if (mIEndTransition != null){
                        mIEndTransition.onCallback();
                    }
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }
        return mTransition;
    }
}
