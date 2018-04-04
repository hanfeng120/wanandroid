package cn.xunger.and.xungerktlibrary.framework

import android.text.InputFilter
import android.text.Spanned

/**
 * Created on 2018/4/3.
 *
 */
class SpaceFilter : InputFilter {
    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence {
        if (source == " ") {
            return ""
        }
        return source ?: ""
    }

}