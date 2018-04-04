package cn.xunger.and.wanandroid

import cn.xunger.and.wanandroid.dagger.AppComponent
import cn.xunger.and.wanandroid.dagger.AppModule
import cn.xunger.and.wanandroid.dagger.DaggerAppComponent
import cn.xunger.and.xungerktlibrary.BaseApplication

/**
 * Created on 2018/4/2.
 *
 */
class WanAndApplication : BaseApplication() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}