package cn.xunger.and.xungerktlibrary.net

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created on 2018/1/24.
 *
 */
class GsonConvertFactoryAgent : Converter.Factory() {

    private val gson: Gson = Gson()

    override fun responseBodyConverter(type: Type?, annotations: Array<Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return ResponseBodyConverter(adapter)
    }

    inner class ResponseBodyConverter<T>(private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {
        override fun convert(value: ResponseBody): T {
            try {
                return adapter.fromJson(value.string())
            } catch (exception: Exception) {
                throw Exception()
            } finally {
                value.close()
            }
        }

    }

}