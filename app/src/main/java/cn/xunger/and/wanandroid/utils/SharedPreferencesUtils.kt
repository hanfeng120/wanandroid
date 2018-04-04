package cn.xunger.and.wanandroid.utils

import android.content.Context

/**
 * Created on 2018/4/4.
 *
 */
class SharedPreferencesUtils {

    companion object {

        private const val SHARED_PREFERENCES_NAME = "WanAndroid"

        fun getString(context: Context, key: String, defValue: String): String {
            return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                    .getString(key, defValue)
        }

        fun getBoolean(context: Context, key: String, defValue: Boolean): Boolean {
            return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                    .getBoolean(key, defValue)
        }

        fun getInt(context: Context, key: String, defValue: Int): Int {
            return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                    .getInt(key, defValue)
        }

        fun saveString(context: Context, key: String, value: String): Boolean {
            return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                    .edit()
                    .putString(key, value)
                    .commit()
        }

        fun saveBoolean(context: Context, key: String, value: Boolean): Boolean {
            return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                    .edit()
                    .putBoolean(key, value)
                    .commit()
        }

        fun saveInt(context: Context, key: String, value: Int): Boolean {
            return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                    .edit()
                    .putInt(key, value)
                    .commit()
        }
    }
}