package com.wanandroid.bridge.ext
import com.wanandroid.bridge.utils.XLog

/**
 *  @description:XLog日志扩展类
 *  @author xcl qq:244672784
 *  @Date 2020/6/30
 **/

fun Any?.logV(tag: String? = null) =
    XLog.logType(XLog.V, tag, XLog.STACK_TRACE_INDEX_7, this)

fun Any?.logD(tag: String? = null) =
    XLog.logType(XLog.D, tag, XLog.STACK_TRACE_INDEX_7, this)

fun Any?.logI(tag: String? = null) =
    XLog.logType(XLog.I, tag, XLog.STACK_TRACE_INDEX_7, this)

fun Any?.logW(tag: String? = null) =
    XLog.logType(XLog.W, tag, XLog.STACK_TRACE_INDEX_7, this)

fun Any?.logE(tag: String? = null) =
    XLog.logType(XLog.E, tag, XLog.STACK_TRACE_INDEX_7, this)

fun Any?.logA(tag: String? = null) =
    XLog.logType(XLog.A, tag, XLog.STACK_TRACE_INDEX_7, this)

fun String?.logXml(tag: String? = null) =
    XLog.logXmlOrJson(XLog.XML, tag, XLog.STACK_TRACE_INDEX_7, this)

fun String?.logJson(tag: String? = null) =
    XLog.logXmlOrJson(XLog.JSON, tag, XLog.STACK_TRACE_INDEX_7, this)
