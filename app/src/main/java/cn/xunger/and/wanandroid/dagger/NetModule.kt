package cn.xunger.and.wanandroid.dagger

import android.app.Application
import cn.xunger.and.wanandroid.network.IApiRoutes
import cn.xunger.and.wanandroid.network.WanCookieJar
import cn.xunger.and.xungerktlibrary.net.XungerKtRetrofitApiHolder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created on 2018/4/2.
 *
 */
@Module
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofitHelper(context: Application): XungerKtRetrofitApiHolder<IApiRoutes> {
        return XungerKtRetrofitApiHolder("http://www.wanandroid.com/", IApiRoutes::class.java,
                WanCookieJar.getInstance(context))
    }
}