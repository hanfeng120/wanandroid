package cn.xunger.and.wanandroid.mainpage

import android.os.Bundle
import android.support.design.widget.Snackbar
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
import kotlinx.android.synthetic.main.fragment_user.*

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
            hostActivity.logout()
            refreshLoginStatus()
            Snackbar.make(tvLogout, "您已退出登录", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshLoginStatus()
    }

    private fun refreshLoginStatus() {
        if (userGlobal.userToken.isNotEmpty()) {
            refreshLoggedStatus()
        } else {
            refreshUnloginStatus()
        }
    }

    private fun refreshLoggedStatus() {
        rl_user_info.visibility = View.VISIBLE
        tv_user_name.text = userGlobal.userName
        tv_slogan.text = "stay hungry, stay foolish"//占位符
        tvLogout.visibility = View.VISIBLE
        ll_login.visibility = View.GONE
    }

    private fun refreshUnloginStatus() {
        rl_user_info.visibility = View.GONE
        tvLogout.visibility = View.GONE
        ll_login.visibility = View.VISIBLE
    }

}