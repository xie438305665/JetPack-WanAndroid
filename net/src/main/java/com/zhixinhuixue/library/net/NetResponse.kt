package com.zhixinhuixue.library.net

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/17
 * 描述　: 服务器返回数据的基类
 * 如果需要帮你做脱壳处理请继承它！！请注意：
 * 2.必须实现抽象方法，根据业务判断返回请求结果是否成功
 */
abstract class NetResponse<T> {

    //抽象方法，基类继承该类时，需要重写该方法
    abstract fun isSuccess(): Boolean

    abstract fun getResponseData(): T

    abstract fun getResponseCode(): String

    abstract fun getResponseMsg(): String

}