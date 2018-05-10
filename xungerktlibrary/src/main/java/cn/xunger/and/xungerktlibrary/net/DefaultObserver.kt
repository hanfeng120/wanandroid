package cn.xunger.and.xungerktlibrary.net

import cn.xunger.and.xungerktlibrary.interfaces.ICallBack
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Response
import java.net.ConnectException

/**
 * Created on 2018/1/24.
 *
 */
abstract class DefaultObserver<T : IResponse>(val callback: ICallBack<T>) : Observer<Response<T>> {

    override fun onSubscribe(d: Disposable) {
        callback.onSubscribe(d)
    }

    override fun onNext(response: Response<T>) {
        val body: T? = response.body()
        when {
            body == null -> onError(ConnectException())
            body.isSuccess() -> onSuccess(body)
            else -> onError(null, body)
        }
    }

    final override fun onError(e: Throwable) {
        onError(e, null)
    }

    override fun onComplete() {
    }

    abstract fun onSuccess(result: T)

    open fun onError(e: Throwable?, result: T?) {
        callback.onError(e, result)
    }

}