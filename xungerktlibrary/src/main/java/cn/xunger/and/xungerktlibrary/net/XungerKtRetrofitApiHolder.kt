package cn.xunger.and.xungerktlibrary.net

import okhttp3.OkHttpClient

/**
 * Created on 2018/1/23.
 *
 */
class XungerKtRetrofitApiHolder<T>(private val hostUrl: String, private val apiClass: Class<T>) : IRetrofitApiHolder<T> {

    private val iApiHelper: T? = null

    override fun getRetrofitApiHolder(): T {
        return iApiHelper ?: generateXungerKtRetrofitApiHelper()
    }

    private fun generateXungerKtRetrofitApiHelper(): T {
        val okHttpBuilder = generateOkHttpClient()
        val retrofitBuilder = DefaultRetrofitBuilder.instance.getRetrofitBuilder(hostUrl)

        return retrofitBuilder.client(okHttpBuilder)
                .build()
                .create(apiClass)
    }

    private fun generateOkHttpClient(): OkHttpClient {
        return DefaultOkHttpClient.instance.getClientBuilder().build()
    }
}