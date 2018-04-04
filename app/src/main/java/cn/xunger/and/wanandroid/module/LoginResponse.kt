package cn.xunger.and.wanandroid.module

/**
 * Created on 2018/4/3.
 *
 */
class LoginResponse : BaseResponse() {

    lateinit var data: LoginData

    class LoginData {
        var type: Int = 0
        lateinit var collectIds: Array<String>
        lateinit var id: String
        lateinit var username: String
    }
}