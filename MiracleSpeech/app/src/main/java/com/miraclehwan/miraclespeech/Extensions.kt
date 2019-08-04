package com.miraclehwan.miraclespeech

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.miraclehwan.miraclegithub.util.Log
import com.miraclehwan.miraclespeech.adapter.ResultAdapter

object Extensions {

    @JvmStatic
    @BindingAdapter("isPlay")
    fun setPlayLottie(view: LottieAnimationView, isPlay: Boolean) {
        if (isPlay) {
            view.playAnimation()
        } else {
            view.cancelAnimation()
        }
    }

    @JvmStatic
    @BindingAdapter("animVisible")
    fun setVisible(view: View, isVisible: Int) {
        if (view.visibility == isVisible) {
            return
        }
        val valueAnimator = ValueAnimator().apply {
            setFloatValues(
                if (isVisible == View.VISIBLE) 0.0f else 1.0f,
                if (isVisible == View.VISIBLE) 1.0f else 0.0f
            )
            duration = 1000
            addUpdateListener { anim ->
                view.alpha = anim.animatedValue as Float
            }
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationEnd(animation: Animator?) {
                    if (isVisible == View.GONE) {
                        view.visibility = View.GONE
                    }
                }

                override fun onAnimationStart(animation: Animator?) {
                    if (isVisible == View.VISIBLE) {
                        view.alpha = 0.0f
                        view.visibility = View.VISIBLE
                    }
                }
            })
        }
        valueAnimator.start()
    }

    @JvmStatic
    @BindingAdapter("setItem")
    fun setItem(view: RecyclerView, items: MutableList<String>?) {
        if (items == null) {
            return
        }
        val adapter = view.adapter as? ResultAdapter ?: ResultAdapter().apply {
            view.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
            view.adapter = this
        }
        adapter.setItems(items)
    }
}