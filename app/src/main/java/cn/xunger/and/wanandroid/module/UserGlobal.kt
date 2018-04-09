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
    lateinit var userPassword: String

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
        userName = SharedPreferencesUtils.getString(context, Constants.KEY_USER_NAME, "")
        userPassword = SharedPreferencesUtils.getString(context, Constants.KEY_USER_PWD, "")
    }

    fun saveUserToken(password: String) {
        this.userPassword = password
        SharedPreferencesUtils.saveString(context, Constants.KEY_USER_PWD, password)
    }

    fun saveUserName(userName: String) {
        this.userName = userName
        SharedPreferencesUtils.saveString(context, Constants.KEY_USER_NAME, userName)
    }

    fun clearUserToken() {
        userPassword = ""
        SharedPreferencesUtils.saveString(context, Constants.KEY_USER_PWD, "")
    }

}
