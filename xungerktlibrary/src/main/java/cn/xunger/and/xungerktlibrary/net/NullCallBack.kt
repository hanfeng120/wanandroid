package cn.xunger.and.xungerktlibrary.net

import cn.xunger.and.xungerktlibrary.interfaces.ICallBack
import io.reactivex.disposables.Disposable

/**
 * Created on 2018/5/10.
 *
 */
class NullCallBack : ICallBack<IResponse> {

    override fun onSubscribe(d: Disposable) {
    }

    override fun onError(e: Throwable?, result: IResponse?) {
    }

}