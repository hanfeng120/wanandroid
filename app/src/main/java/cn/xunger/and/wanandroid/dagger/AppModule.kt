package cn.xunger.and.wanandroid.dagger

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
    fun provideApplication(): WanAndApplication {
        return app
    }
}