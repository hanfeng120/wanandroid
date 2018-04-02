package cn.xunger.and.xungerktlibrary.net

/**
 * Created on 2018/1/23.
 *
 */
interface IRetrofitApiHolder<out T> {

    fun getRetrofitApiHolder(): T
}