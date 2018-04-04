package cn.xunger.and.wanandroid.dagger

import android.app.Application
import android.content.Context
import cn.xunger.and.wanandroid.module.UserGlobal
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created on 2018/4/4.
 *
 */
@Module
class GlobalModule {

    @Singleton
    @Provides
    fun provideUserGlobal(context: Application): UserGlobal {
        return UserGlobal.getUserGlobal(context)
    }
}