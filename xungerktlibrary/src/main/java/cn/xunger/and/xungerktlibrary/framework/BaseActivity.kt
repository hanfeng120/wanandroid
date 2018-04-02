package cn.xunger.and.xungerktlibrary.framework

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import java.lang.ref.WeakReference

/**
 * Created on 2018/1/23.
 *
 */
abstract class BaseActivity : AppCompatActivity() {

    private val innerHandler: InnerHandler

    init {
        innerHandler = InnerHandler(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    abstract fun getLayoutResId(): Int

    private fun init() {
        if (handleIntent(intent)) {
            initView()
            initData()
        } else {
            finish()
        }
    }

    abstract fun initView()

    abstract fun initData()

    open fun handleIntent(intent: Intent): Boolean {
        return true
    }

    fun getContext(): Context {
        return this
    }

    fun handleMessage(msg: Message?) {

    }

    class InnerHandler(baseActivity: BaseActivity) : Handler() {
        private val reference: WeakReference<BaseActivity> = WeakReference(baseActivity)

        override fun handleMessage(msg: Message?) {
            if (reference.get() != null) {
                reference.get()!!.handleMessage(msg)
            }
        }
    }

}