package cn.xunger.and.wanandroid.framework

import android.os.Bundle
import cn.xunger.and.wanandroid.WanAndApplication
import cn.xunger.and.wanandroid.module.UserGlobal
import cn.xunger.and.wanandroid.network.IApiRoutes
import cn.xunger.and.wanandroid.network.WanCookieJar
import cn.xunger.and.xungerktlibrary.framework.BaseActivity
import cn.xunger.and.xungerktlibrary.net.XungerKtRetrofitApiHolder
import javax.inject.Inject

/**
 * Created on 2018/4/2.
 *
 */
abstract class CommonActivity : BaseActivity() {

    @Inject
    lateinit var apiHelper: XungerKtRetrofitApiHolder<IApiRoutes>

    @Inject
    lateinit var userGlobal: UserGlobal

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

}