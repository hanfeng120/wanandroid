package cn.xunger.and.wanandroid.dagger

import cn.xunger.and.wanandroid.network.IApiRoutes
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
    fun provideRetrofitHelper(): XungerKtRetrofitApiHolder<IApiRoutes> {
        return XungerKtRetrofitApiHolder("", IApiRoutes::class.java)
    }
}