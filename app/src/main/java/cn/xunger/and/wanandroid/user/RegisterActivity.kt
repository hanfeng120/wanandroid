package cn.xunger.and.wanandroid.user

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.framework.CommonActivity
import cn.xunger.and.wanandroid.module.RegisterResponse
import cn.xunger.and.xungerktlibrary.framework.SpaceFilter
import cn.xunger.and.xungerktlibrary.net.DefaultObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : CommonActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
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
        btn_register.setOnClickListener {
            when {
                et_user_name.text.isEmpty() -> til_user_name.error = "用户名不可为空"
                et_password.text.isEmpty() -> til_password.error = "密码不可为空"
                else -> {
                    register()
                }
            }
        }
    }

    private fun register() {
        apiHelper.getRetrofitApiHolder()
                .register(et_user_name.text.toString(), et_password.text.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : DefaultObserver<RegisterResponse>(this) {

                    override fun onSuccess(result: RegisterResponse) {
                        Log.d("Register", "onSuccess: ")
                    }

                })
    }

}
