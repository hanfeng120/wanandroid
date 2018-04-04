package cn.xunger.and.xungerktlibrary.framework

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.common_toolbar.*
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
            initToolbar()
            initView()
            initData()
        } else {
            finish()
        }
    }

    private fun initToolbar() {
        if (tb_toolbar != null) {
            setSupportActionBar(tb_toolbar)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            if (isShowNavigationIcon()) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                tb_toolbar.setNavigationOnClickListener {
                    onNavigationIconClicked()
                }
            }
        }
    }

    protected open fun onNavigationIconClicked() {
        onBackPressed()
    }

    protected open fun isShowNavigationIcon(): Boolean {
        return true
    }

    override fun onTitleChanged(title: CharSequence?, color: Int) {
        super.onTitleChanged(title, color)
        if (tv_toolbar_title != null) {
            tv_toolbar_title.text = title
        }
    }

    abstract fun initView()

    abstract fun initData()

    protected fun hideSoftInput() {
        if (this.currentFocus != null && this.currentFocus!!.windowToken != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(this.currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    open fun handleIntent(intent: Intent): Boolean {
        return true
    }

    protected fun getContext(): Context {
        return this
    }

    protected fun getHandler(): Handler {
        return innerHandler
    }

    protected fun sendEmptyMessage(what: Int) {
        innerHandler.sendEmptyMessage(what)
    }

    protected fun sendEmptyMessageDelayed(what: Int, delayMillis: Long) {
        innerHandler.sendEmptyMessageDelayed(what, delayMillis)
    }

    protected open fun handleMessage(msg: Message?) {

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