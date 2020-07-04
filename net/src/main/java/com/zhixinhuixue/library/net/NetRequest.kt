package com.zhixinhuixue.library.net

import com.zhixinhuixue.library.net.api.HomeApi
import com.zhixinhuixue.library.net.api.NetUrl
import com.zhixinhuixue.library.net.api.UserApi

/**
 * 作者　: hegaojian
 * 时间　: 2020/6/28
 */

//单例 封装首页模块的请求Api 方便直接快速调用简单的接口
val homeApi: HomeApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetRetrofit.getApi(HomeApi::class.java, NetUrl.DEV_URL)
}

//单例 封装个人模块的请求Api 方便直接快速调用简单的接口
val userApi: UserApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetRetrofit.getApi(UserApi::class.java, NetUrl.DEV_URL)
}




