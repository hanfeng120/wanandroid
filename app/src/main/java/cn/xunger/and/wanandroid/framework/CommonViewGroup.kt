package cn.xunger.and.wanandroid.framework

import android.content.Context
import cn.xunger.and.wanandroid.WanAndApplication
import cn.xunger.and.wanandroid.network.IApiRoutes
import cn.xunger.and.xungerktlibrary.framework.BaseViewGroup
import cn.xunger.and.xungerktlibrary.net.XungerKtRetrofitApiHolder
import javax.inject.Inject

/**
 * Created on 2018/4/12.
 *
 */
abstract class CommonViewGroup(context: Context) : BaseViewGroup(context) {

    @Inject
    lateinit var apiHelper: XungerKtRetrofitApiHolder<IApiRoutes>

    override fun injectApiHelper() {
        WanAndApplication.appComponent.inject(this)
    }
}