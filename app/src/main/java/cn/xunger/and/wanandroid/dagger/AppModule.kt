package cn.xunger.and.wanandroid.dagger

import android.app.Application
import android.content.Context
import cn.xunger.and.wanandroid.WanAndApplication
import dagger.Module
import dagger.Provides

/**
 * Created on 2018/4/2.
 *
 */
@Module
class AppModule(val app: WanAndApplication) {

    @Provides
    fun provideWanApplication(): WanAndApplication {
        return app
    }

    @Provides
    fun provideApplication(): Application {
        return app
    }

    @Provides
    fun provideContext(): Context {
        return app.applicationContext
    }
}