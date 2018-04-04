package cn.xunger.and.wanandroid.module

import android.app.Application
import cn.xunger.and.wanandroid.common.Constants
import cn.xunger.and.wanandroid.utils.SharedPreferencesUtils

/**
 * Created on 2018/4/4.
 *
 */
class UserGlobal private constructor(val context: Application) {

    lateinit var userId: String
    lateinit var userName: String
    lateinit var userToken: String

    companion object {

        private var userGlobal: UserGlobal? = null

        fun getUserGlobal(context: Application): UserGlobal {
            if (userGlobal == null) {
                userGlobal = UserGlobal(context)
            }
            return userGlobal!!
        }
    }

    init {
        initState()
    }

    private fun initState() {
        userToken = SharedPreferencesUtils.getString(context, Constants.KEY_USER_TOKEN, "")
        userName = SharedPreferencesUtils.getString(context, Constants.KEY_USER_NAME, "")
    }

    fun saveUserToken(token: String) {
        userToken = token
        SharedPreferencesUtils.saveString(context, Constants.KEY_USER_TOKEN, token)
    }

    fun clearUserToken() {
        userToken = ""
        SharedPreferencesUtils.saveString(context, Constants.KEY_USER_TOKEN, "")
    }

}
