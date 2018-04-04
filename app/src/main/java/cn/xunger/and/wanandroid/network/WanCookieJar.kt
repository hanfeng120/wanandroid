package cn.xunger.and.wanandroid.network

import android.app.Application
import cn.xunger.and.wanandroid.module.UserGlobal
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.Cookie
import okhttp3.HttpUrl

/**
 * Created on 2018/4/3.
 *
 */
class WanCookieJar(var context: Application) : PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context)) {

    companion object {

        private var wanCookieJar: WanCookieJar? = null

        fun getInstance(context: Application): WanCookieJar {
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

    override fun saveFromResponse(url: HttpUrl?, cookies: MutableList<Cookie>?) {
        if (cookies != null) {
            for (cookie in cookies.iterator()) {
                if (cookie.name() == "JSESSIONID") {
                    UserGlobal.getUserGlobal(context).saveUserToken(cookie.value())
                }
            }
        }
        super.saveFromResponse(url, cookies)
    }

    override fun clear() {
        super.clear()
        super.clearSession()
    }

}