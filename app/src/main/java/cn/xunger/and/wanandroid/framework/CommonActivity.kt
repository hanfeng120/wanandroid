package cn.xunger.and.wanandroid.framework

import android.os.Bundle
import cn.xunger.and.wanandroid.WanAndApplication
import cn.xunger.and.wanandroid.network.IApiRoutes
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

    override fun onCreate(savedInstanceState: Bundle?) {
        WanAndApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

}