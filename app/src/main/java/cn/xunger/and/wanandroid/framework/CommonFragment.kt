package cn.xunger.and.wanandroid.framework

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import cn.xunger.and.wanandroid.WanAndApplication
import cn.xunger.and.wanandroid.module.UserGlobal
import cn.xunger.and.wanandroid.network.IApiRoutes
import cn.xunger.and.xungerktlibrary.net.XungerKtRetrofitApiHolder
import javax.inject.Inject

/**
 * Created on 2018/4/3.
 *
 */
open class CommonFragment : Fragment() {

    @Inject
    protected lateinit var userGlobal: UserGlobal

    @Inject
    protected lateinit var apiHelper: XungerKtRetrofitApiHolder<IApiRoutes>

    protected lateinit var hostActivity: CommonActivity

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        WanAndApplication.appComponent.inject(this)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is CommonActivity) {
            hostActivity = context as CommonActivity
        }
    }

}