package cn.xunger.and.xungerktlibrary.net

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created on 2018/1/23.
 *
 */
class DefaultOkHttpClient {
    companion object {
        val instance: DefaultOkHttpClient by lazy {
            DefaultOkHttpClient()
        }
    }

    fun getClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
    }

}