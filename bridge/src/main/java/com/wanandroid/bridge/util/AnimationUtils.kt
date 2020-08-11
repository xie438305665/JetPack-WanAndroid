package com.wanandroid.bridge.util

import android.animation.Animator
import android.animation.ValueAnimator

/**
 *  @description:属性动画
 *  @author xcl qq:244672784
 *  @Date 2020/8/8
 **/
object AnimationUtils {

    private var valueAnimation: ValueAnimator? = null

    private var listener: Animator.AnimatorListener? = null

    private var updateListener: ValueAnimator.AnimatorUpdateListener? = null

    /**
     * 绑定动画监听 (必须先执行这个)
     */
    fun bindListener(
        vararg values: Int,
        defaultDuration: Long = 1000,
        listener: Animator.AnimatorListener?,
        updateListener: ValueAnimator.AnimatorUpdateListener?
    ) {
        listener ?: return
        this.listener = listener
        this.updateListener = updateListener
        valueAnimation = ValueAnimator().apply {
            setIntValues(*values)
            duration = defaultDuration
            addUpdateListener(updateListener)
            addListener(listener)
        }
    }

    /**
     * 移除特定监听
     */
    fun removeListener() {
        listener ?: return
        updateListener ?: return
        valueAnimation?.removeListener(listener)
        valueAnimation?.removeUpdateListener(updateListener)
    }

    /**
     * 移除所有监听
     */
    fun removeAllListener() {
        valueAnimation?.removeAllUpdateListeners()
        valueAnimation?.removeAllListeners()
    }

    /**
     * 动画开始
     */
    fun start() {
        listener ?: throw NullPointerException("listener为null 请先执行 bindListener方法")
        updateListener ?: throw  NullPointerException("updateListener为null 请先执行 bindListener方法")
        valueAnimation?.start()
    }

    /**
     * 动画取消
     */
    fun cancel() {
        removeListener()
        removeAllListener()
        valueAnimation?.cancel()
    }
}
