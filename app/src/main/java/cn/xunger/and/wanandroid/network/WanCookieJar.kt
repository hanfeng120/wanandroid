package cn.xunger.and.wanandroid.network

import android.content.Context
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor

/**
 * Created on 2018/4/3.
 *
 */
class WanCookieJar(context: Context) : PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context)) {


    companion object {

        private var wanCookieJar: WanCookieJar? = null

        fun getInstance(context: Context): WanCookieJar {
            if (wanCookieJar == null) {
                synchronized(WanCookieJar::class) {
                    if (wanCookieJar == null) {
                        wanCookieJar = WanCookieJar(context)
                    }
                }
            }
            return wanCookieJar!!
        }
    }

}