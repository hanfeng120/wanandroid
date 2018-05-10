package cn.xunger.and.xungerktlibrary.interfaces

import cn.xunger.and.xungerktlibrary.net.IResponse
import io.reactivex.disposables.Disposable

/**
 * Created on 2018/5/10.
 *
 */
interface ICallBack<in T : IResponse> {

    fun onSubscribe(d: Disposable)

    fun onError(e: Throwable?, result: T?)
}