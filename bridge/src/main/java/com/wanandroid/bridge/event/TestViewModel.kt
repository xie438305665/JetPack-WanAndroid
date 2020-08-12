package com.wanandroid.bridge.event

import androidx.lifecycle.ViewModel
import com.wanandroid.bridge.AppConfig
import com.wanandroid.bridge.annotation.EventBusKey

/**
 *  @description:App配置类
 *  @author xcl qq:244672784
 *  @Date 2020/8/12
 **/
class TestViewModel : ViewModel() {
    val test get() = _test
    private var _test: EventLiveData<Any> = EventLiveData()

    init {
        _test.value =0
    }
}