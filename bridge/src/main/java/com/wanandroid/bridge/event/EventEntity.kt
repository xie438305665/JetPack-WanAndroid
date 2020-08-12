package com.wanandroid.bridge.event

import com.wanandroid.bridge.annotation.EventBusKey


/**
 *  @description:LiveEventBus 数据携带者   key作为唯一标识
 *  @author xcl qq:244672784
 *  @Date 2020/7/11
 **/
data class EventEntity<T>(@EventBusKey val key: String, var data: T) {


}

