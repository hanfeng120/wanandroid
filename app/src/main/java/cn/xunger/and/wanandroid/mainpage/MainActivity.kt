package cn.xunger.and.wanandroid.mainpage

import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.framework.CommonActivity

class MainActivity : CommonActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun isShowNavigationIcon(): Boolean {
        return false
    }

}
