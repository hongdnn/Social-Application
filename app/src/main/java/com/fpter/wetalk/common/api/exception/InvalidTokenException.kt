package com.fpter.wetalk.common.api.exception

class InvalidTokenException(private var needLoginAgain: Boolean) : RuntimeException() {
    fun needLoginAgain(): Boolean {
        return needLoginAgain
    }

    fun setNeedLoginAgain(needLoginAgain: Boolean) {
        this.needLoginAgain = needLoginAgain
    }
}