package cn.xunger.and.wanandroid.interfaces

import android.view.View

/**
 * Created on 2018/4/9.
 *
 */
interface OnItemClickListener<in T> {
    fun onItemClick(position: Int, data: T, view: View)
}