package cn.xunger.and.wanandroid.mainpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.framework.CommonFragment
import cn.xunger.and.wanandroid.user.LoginActivity
import cn.xunger.and.xungerktlibrary.utils.startNewActivity

/**
 * Created on 2018/4/3.
 *
 */
class UserFragment : CommonFragment() {

    private lateinit var llLogin: LinearLayout
    private lateinit var rlCollection: RelativeLayout
    private lateinit var rlAboutUs: RelativeLayout
    private lateinit var tvLogout: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_user, container, false)
        llLogin = rootView.findViewById(R.id.ll_login)
        rlCollection = rootView.findViewById(R.id.rl_collection)
        rlAboutUs = rootView.findViewById(R.id.rl_about_us)
        tvLogout = rootView.findViewById(R.id.tv_logout)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        llLogin.setOnClickListener {
            startNewActivity<LoginActivity>()
        }
        rlCollection.setOnClickListener {

        }
        rlAboutUs.setOnClickListener {

        }
        tvLogout.setOnClickListener {

        }
    }
}