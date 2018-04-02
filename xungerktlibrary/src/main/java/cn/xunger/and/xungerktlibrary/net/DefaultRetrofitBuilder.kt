package cn.xunger.and.xungerktlibrary.net

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created on 2018/1/23.
 *
 */
class DefaultRetrofitBuilder {

    companion object {
        val instance: DefaultRetrofitBuilder by lazy {
            DefaultRetrofitBuilder()
        }
    }

    fun getRetrofitBuilder(hostUrl: String): Retrofit.Builder {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(hostUrl)
    }

}