package com.wanandroid.bridge.event

import androidx.lifecycle.ViewModel
import com.wanandroid.bridge.AppConfig
import com.wanandroid.bridge.annotation.EventBusKey

/**
 *  @description:App配置类
 *  @author xcl qq:244672784
 *  @Date 2020/8/12
 **/
class ConfigViewModel : ViewModel() {
    val configVm get() = _configVm
    private var _configVm: EventLiveData<EventEntity<AppConfig>> = EventLiveData()

    init {
        _configVm.value = EventEntity(EventBusKey.CONFIG, AppConfig())
    }
}