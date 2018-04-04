package cn.xunger.and.wanandroid.network

import cn.xunger.and.wanandroid.module.LoginResponse
import cn.xunger.and.wanandroid.module.RegisterResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created on 2018/4/2.
 *
 */
interface IApiRoutes {

    @FormUrlEncoded
    @POST("user/register")
    fun register(@Field("username") username: String,
                 @Field("password") password: String,
                 @Field("repassword") repassword: String = password): Observable<Response<RegisterResponse>>

    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<Response<LoginResponse>>

}