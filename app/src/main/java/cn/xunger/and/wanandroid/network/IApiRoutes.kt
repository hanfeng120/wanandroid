package cn.xunger.and.wanandroid.network

import cn.xunger.and.wanandroid.module.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

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

    @GET("/article/list/{page}/json")
    fun loadArticleList(@Path("page") page: String = "0", @Query("cid") cid: String? = null): Observable<Response<ArticleResponse>>

    @GET("/banner/json")
    fun loadHomeBannerData(): Observable<Response<HomeBannerResponse>>

    @GET("/tree/json")
    fun loadKnowledgeTreeData(): Observable<Response<KnowledgeTreeResponse>>
}