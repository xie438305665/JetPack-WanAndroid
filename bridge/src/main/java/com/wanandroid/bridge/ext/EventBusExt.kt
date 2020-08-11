package com.wanandroid.bridge.ext

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus
import com.wanandroid.bridge.AppConfig
import com.wanandroid.bridge.base.EventBusEntity

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @Date 2020/7/11
 **/

/**
 * LiveEventBus 发送消息
 */
fun  postEvent(@Nullable key: String, data: EventBusEntity) {
    LiveEventBus.get(key).post(data)
}

/**
 * LiveEventBus 发送消息
 */
fun postEvent(data: EventBusEntity) {
    LiveEventBus.get(EventBusEntity::class.java).post(data)
}

/**
 * LiveEventBus 接收消息
 */
fun observeEvent(
    key: String,
    @NonNull owner: LifecycleOwner,
    block: (EventBusEntity) -> Unit
) {
    LiveEventBus.get(key,EventBusEntity::class.java).observe(owner, Observer {
        block(it)
    })
}

/**
 * LiveEventBus 接收消息
 */
fun observeEvent(
    @NonNull owner: LifecycleOwner,
    block: (EventBusEntity) -> Unit
) {
    LiveEventBus.get(EventBusEntity::class.java)
        .observe(owner, Observer { block(it as EventBusEntity) })
}

/**
 * LiveEventBus 接收消息
 */
fun observeSticky(
    key: String,
    @NonNull owner: LifecycleOwner,
    block: (EventBusEntity) -> Unit
) {
    LiveEventBus.get(key).observeSticky(owner, Observer { block(it as EventBusEntity) })
}

/**
 * LiveEventBus 接收消息
 */
fun observeSticky(
    @NonNull owner: LifecycleOwner,
    block: (EventBusEntity) -> Unit
) {
    LiveEventBus.get(EventBusEntity::class.java)
        .observeSticky(owner, Observer { block(it as EventBusEntity) })
}
