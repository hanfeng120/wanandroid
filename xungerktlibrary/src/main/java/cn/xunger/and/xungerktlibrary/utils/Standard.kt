package cn.xunger.and.xungerktlibrary.utils

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import android.support.v4.app.Fragment

/**
 * Created on 2018/3/13.
 *
 */
inline fun <reified T : Activity> Activity.startNewActivity(parameter: Parcelable) {
    val intent = Intent(this, T::class.java).apply {
        val name = getExtraName(T::class.java)
        putExtra(name, parameter)
    }
    startActivity(intent)
}

inline fun <reified T : Activity> Activity.startNewActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

inline fun <reified T : Activity> Activity.startNewActivityAndFinish() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
    finish()
}

inline fun <reified T : Activity> Fragment.startNewActivity() {
    val intent = Intent(context, T::class.java)
    startActivity(intent)
}

inline fun <reified T : Parcelable> Activity.getParameter(): T {
    val name = getExtraName(this::class.java)
    return intent.getParcelableExtra(name)
}

fun getExtraName(target: Class<out Activity>): String {
    return "${target.canonicalName}.Parameter"
}
