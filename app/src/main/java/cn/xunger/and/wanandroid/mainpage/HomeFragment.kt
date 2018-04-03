package cn.xunger.and.wanandroid.mainpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.framework.CommonFragment

/**
 * Created on 2018/4/3.
 *
 */
class HomeFragment : CommonFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}