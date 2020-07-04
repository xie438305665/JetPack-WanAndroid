package com.zhixinhuixue.library.net.error

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/17
 * 描述　:自定义错误信息异常
 */
class NetException : Exception {

    var errorMsg: String = ""//错误消息
    var errCode: String? = "" //错误码
    var errorLog: String? = "" //错误日志

    constructor(errCode: String?, error: String?, errorLog: String? = "") : super(error) {
        this.errorMsg = error ?: "请求失败，请稍后再试"
        this.errCode = errCode
        this.errorLog = errorLog?:this.errorMsg
    }

    constructor(errorType: ErrorType, e: Throwable?) {
        errCode = errorType.getKey().toString()
        errorMsg = errorType.getValue()
        errorLog = e?.message
    }

    constructor(msg: String?) {
        errorMsg = msg ?: ""
        errorLog = errorMsg
    }
}