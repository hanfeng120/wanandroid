package cn.xunger.and.xungerktlibrary.framework

import android.content.Context
import android.view.LayoutInflater
import android.view.View

/**
 * Created on 2018/4/12.
 *
 */
abstract class BaseViewGroup(val context: Context) {

    lateinit var rootView: View

    fun createView(view: View?) {
        rootView = view ?: LayoutInflater.from(context).inflate(getLayoutRes(), null)
    }

    abstract fun getLayoutRes(): Int

    fun init() {
        injectApiHelper()
        initView()
        initData()
    }

    protected abstract fun injectApiHelper()

    protected abstract fun initView()

    protected abstract fun initData()

}