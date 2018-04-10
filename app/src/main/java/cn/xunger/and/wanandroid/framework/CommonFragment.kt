package cn.xunger.and.wanandroid.framework

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import cn.xunger.and.wanandroid.WanAndApplication
import cn.xunger.and.wanandroid.module.UserGlobal
import cn.xunger.and.wanandroid.network.IApiRoutes
import cn.xunger.and.xungerktlibrary.net.XungerKtRetrofitApiHolder
import java.lang.ref.WeakReference
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

    private val innerHandler = InnerHandler(this)

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

    protected fun sendEmptyMessage(what: Int) {
        innerHandler.sendEmptyMessage(what)
    }

    protected fun sendEmptyMessageDelayed(what: Int, delayMillis: Long) {
        innerHandler.sendEmptyMessageDelayed(what, delayMillis)
    }

    protected fun removeCallbacksAndMessages() {
        innerHandler.removeCallbacksAndMessages(null)
    }

    protected open fun handleMessage(msg: Message) {

    }

    class InnerHandler(commonFragment: CommonFragment) : Handler() {

        private val weakReference = WeakReference<CommonFragment>(commonFragment)

        override fun handleMessage(msg: Message?) {
            if (weakReference.get() != null && msg != null) {
                weakReference.get()!!.handleMessage(msg)
            }
            super.handleMessage(msg)
        }

    }

}