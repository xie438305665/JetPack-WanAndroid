package com.zhixinhuixue.library.upgrade

import androidx.annotation.NonNull
import kotlin.math.max

/**
 * @Author: cock
 * @Date: 2020/06/30
 * @Description:
 */
class Version(var version: String?) : Comparable<Version> {
    override fun compareTo(@NonNull other: Version): Int {
        version?.let { it ->
            val thisParts: Array<String> = it.split(".").toTypedArray()
            other.version?.let { that ->
                val thatParts: Array<String> = that.split(".").toTypedArray()
                val length = max(thisParts.size, thatParts.size)
                for (i in 0 until length) {
                    val thisPart = if (i < thisParts.size) thisParts[i].toInt() else 0
                    val thatPart = if (i < thatParts.size) thatParts[i].toInt() else 0
                    if (thisPart < thatPart) return -1
                    if (thisPart > thatPart) return 1
                }
            }
        }
        return 0
    }

    override fun equals(other: Any?): Boolean {
        return this === other || other != null && this.javaClass == other.javaClass && this.compareTo(
            other as Version
        ) == 0
    }

    override fun hashCode(): Int {
        return version?.hashCode() ?: 0
    }
}