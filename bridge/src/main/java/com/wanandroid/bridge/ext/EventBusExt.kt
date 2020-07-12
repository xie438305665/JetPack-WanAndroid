package com.wanandroid.bridge.ext

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus
import com.wanandroid.bridge.base.BaseLiveEventBus

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @Date 2020/7/11
 **/

/**
 * LiveEventBus 发送消息
 */
fun <T> LiveEventBus.postEvent(@Nullable key: String, data: T) {
    LiveEventBus.get(key).post(data)
}

/**
 * LiveEventBus 发送消息
 */
fun <T> LiveEventBus.postEvent(key: Int, data: T) {
    LiveEventBus.get(BaseLiveEventBus::class.java).post(BaseLiveEventBus(key, data))
}

/**
 * LiveEventBus 接收消息
 */
fun <T> LiveEventBus.observe(
    key: String,
    @NonNull owner: LifecycleOwner,
    block: (BaseLiveEventBus<T>) -> Unit
) {
    LiveEventBus.get(key).observe(owner, Observer { block(it as BaseLiveEventBus<T>) })
}

/**
 * LiveEventBus 接收消息
 */
fun <T> LiveEventBus.observe(
    key: Int,
    @NonNull owner: LifecycleOwner,
    block: (BaseLiveEventBus<T>) -> Unit
) {
    LiveEventBus.get(BaseLiveEventBus::class.java)
        .observe(owner, Observer { block(it as BaseLiveEventBus<T>) })
}

/**
 * LiveEventBus 接收消息
 */
fun <T> LiveEventBus.observeSticky(
    key: String,
    @NonNull owner: LifecycleOwner,
    block: (BaseLiveEventBus<T>) -> Unit
) {
    LiveEventBus.get(key).observeSticky(owner, Observer { block(it as BaseLiveEventBus<T>) })
}

/**
 * LiveEventBus 接收消息
 */
fun <T> LiveEventBus.observeSticky(
    key: Int,
    @NonNull owner: LifecycleOwner,
    block: (BaseLiveEventBus<T>) -> Unit
) {
    LiveEventBus.get(BaseLiveEventBus::class.java)
        .observeSticky(owner, Observer { block(it as BaseLiveEventBus<T>) })
}
