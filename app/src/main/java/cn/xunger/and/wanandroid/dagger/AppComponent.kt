package cn.xunger.and.wanandroid.dagger

import cn.xunger.and.wanandroid.framework.CommonActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created on 2018/4/2.
 *
 */
@Singleton
@Component(modules = [(AppModule::class), (NetModule::class)])
interface AppComponent {

    fun inject(commonActivity: CommonActivity)

}