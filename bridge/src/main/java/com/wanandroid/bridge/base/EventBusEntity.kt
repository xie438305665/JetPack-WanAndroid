package com.wanandroid.bridge.base

import com.jeremyliao.liveeventbus.core.LiveEvent

/**
 *  @description:LiveEventBus 数据携带者   key作为唯一标识
 *  @author xcl qq:244672784
 *  @Date 2020/7/11
 **/
class EventBusEntity(val key: String, var data: Any?) : LiveEvent {


}

