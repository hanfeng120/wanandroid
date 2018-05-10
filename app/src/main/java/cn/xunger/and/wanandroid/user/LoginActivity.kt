package cn.xunger.and.wanandroid.user

import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.common.Constants
import cn.xunger.and.wanandroid.framework.CommonActivity
import cn.xunger.and.wanandroid.module.LoginResponse
import cn.xunger.and.wanandroid.utils.SharedPreferencesUtils
import cn.xunger.and.xungerktlibrary.framework.SpaceFilter
import cn.xunger.and.xungerktlibrary.net.DefaultObserver
import cn.xunger.and.xungerktlibrary.utils.startNewActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : CommonActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        et_user_name.setText(userGlobal.userName)
        et_user_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_user_name.error = null
            }

        })
        et_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til_password.error = null
            }

        })
        et_user_name.filters = arrayOf(SpaceFilter())
        et_password.filters = arrayOf(SpaceFilter())
    }

    override fun initData() {
        btn_login.setOnClickListener {
            when {
                et_user_name.text.isEmpty() -> til_user_name.error = "用户名不可为空"
                et_password.text.isEmpty() -> til_password.error = "密码不可为空"
                else -> {
                    login()
                }
            }
        }
    }

    private fun login() {
        apiHelper.getRetrofitApiHolder()
                .login(et_user_name.text.toString(), et_password.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultObserver<LoginResponse>(this) {
                    override fun onSuccess(result: LoginResponse) {
                        userGlobal.userId = result.data.id
                        userGlobal.userName = result.data.username
                        SharedPreferencesUtils.saveString(getContext(), Constants.KEY_USER_NAME, result.data.username)
                        Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show()
                        finish()
                    }

                    override fun onError(e: Throwable?, result: LoginResponse?) {
                        super.onError(e, result)
                        et_password.text = null
                    }

                })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.register_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.register) {
            startNewActivity<RegisterActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

}
