package cn.xunger.and.wanandroid.framework

import android.os.Bundle
import cn.xunger.and.wanandroid.WanAndApplication
import cn.xunger.and.wanandroid.module.BaseResponse
import cn.xunger.and.wanandroid.module.UserGlobal
import cn.xunger.and.wanandroid.network.IApiRoutes
import cn.xunger.and.wanandroid.network.WanCookieJar
import cn.xunger.and.xungerktlibrary.interfaces.ICallBack
import cn.xunger.and.xungerktlibrary.framework.BaseActivity
import cn.xunger.and.xungerktlibrary.net.XungerKtRetrofitApiHolder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created on 2018/4/2.
 *
 */
abstract class CommonActivity : BaseActivity(), ICallBack<BaseResponse> {

    @Inject
    lateinit var apiHelper: XungerKtRetrofitApiHolder<IApiRoutes>

    @Inject
    lateinit var userGlobal: UserGlobal

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        WanAndApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    fun logout() {
        userGlobal.clearUserToken()
        WanCookieJar.getInstance(application).clear()
    }

    protected open fun isLoggedIn(): Boolean {
        return userGlobal.userPassword.isNotEmpty()
    }

    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onError(e: Throwable?, result: BaseResponse?) {
        if (result != null) {
            toast(result.errorMsg)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}