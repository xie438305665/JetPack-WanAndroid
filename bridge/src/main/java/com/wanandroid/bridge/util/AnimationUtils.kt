package com.wanandroid.bridge.util

import android.animation.Animator
import android.animation.ValueAnimator
import androidx.core.animation.addListener

/**
 *  @description:属性动画
 *  @author xcl qq:244672784
 *  @Date 2020/8/8
 **/
object AnimationUtils {

    private val valueAnimation: ValueAnimator by lazy {
        ValueAnimator()
    }

    private var listener: Animator.AnimatorListener? = null

    private var updateListener: ValueAnimator.AnimatorUpdateListener? = null

    /**
     * 绑定动画监听 (必须先执行这个)
     */
    fun bindListener(
        listener: Animator.AnimatorListener?,
        updateListener: ValueAnimator.AnimatorUpdateListener?
    ): ValueAnimator {
        listener ?: return valueAnimation
        this.listener = listener
        return valueAnimation.also {
            this.updateListener = updateListener
            valueAnimation.addUpdateListener(this.updateListener)
            it.addListener { listener }
        }
    }

    /**
     * 移除特定监听
     */
    fun removeListener(): ValueAnimator {
        return valueAnimation.also {
            listener ?: return@also
            updateListener ?: return@also
            valueAnimation.removeListener(listener)
            valueAnimation.removeUpdateListener(updateListener)
        }
    }

    /**
     * 移除所有监听
     */
    fun removeAllListener() {
        valueAnimation.removeAllUpdateListeners()
        valueAnimation.removeAllListeners()
    }

    /**
     * 动画开始
     */
    fun start() {
        listener ?: throw NullPointerException("listener为null 请先执行 bindListener方法")
        updateListener ?: throw  NullPointerException("updateListener为null 请先执行 bindListener方法")
        valueAnimation.start()
    }

    fun cancel() {
        valueAnimation.cancel()
    }
}