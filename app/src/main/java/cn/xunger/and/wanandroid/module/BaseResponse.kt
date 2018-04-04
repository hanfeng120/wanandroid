package cn.xunger.and.wanandroid.module

import cn.xunger.and.xungerktlibrary.net.IResponse

/**
 * Created on 2018/4/3.
 *
 */
open class BaseResponse : IResponse {

    var errorCode: Int = -1
    lateinit var errorMsg: String

    override fun isSuccess(): Boolean {
        return errorCode == 0
    }

}