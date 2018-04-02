package cn.xunger.and.wanandroid

import cn.xunger.and.wanandroid.dagger.AppModule
import cn.xunger.and.wanandroid.dagger.DaggerAppComponent
import cn.xunger.and.xungerktlibrary.BaseApplication

/**
 * Created on 2018/4/2.
 *
 */
class WanAndApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}