package cn.xunger.and.wanandroid

import android.os.Message
import cn.xunger.and.wanandroid.framework.CommonActivity
import cn.xunger.and.wanandroid.mainpage.MainActivity
import cn.xunger.and.xungerktlibrary.utils.startNewActivityAndFinish

class SplashActivity : CommonActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
    }

    override fun initData() {
        sendEmptyMessageDelayed(2001, 1000)
    }

    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)
        startNewActivityAndFinish<MainActivity>()
    }

}
