package cn.xunger.and.wanandroid.knowledge

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.`interface`.OnItemClickListener
import cn.xunger.and.wanandroid.framework.CommonViewGroup
import cn.xunger.and.wanandroid.mainpage.home.ArticleAdapter
import cn.xunger.and.wanandroid.module.ArticleResponse
import cn.xunger.and.wanandroid.web.WebViewActivity
import cn.xunger.and.xungerktlibrary.module.SingleExtra
import cn.xunger.and.xungerktlibrary.net.DefaultObserver
import cn.xunger.and.xungerktlibrary.utils.startNewActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on 2018/4/12.
 *
 */
class KnowledgeTreeViewGroup(context: Context, val cid: String) : CommonViewGroup(context) {

    private lateinit var recyclerView: RecyclerView
    private val adapter = ArticleAdapter(context)

    override fun getLayoutRes(): Int {
        return R.layout.recyclerview
    }

    override fun initView() {
        recyclerView = rootView as RecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter.onItemClickListener = object : OnItemClickListener<ArticleResponse.Article> {
            override fun onItemClick(position: Int, data: ArticleResponse.Article, view: View) {
                context.startNewActivity<WebViewActivity>(SingleExtra(data.link))
            }

        }
    }

    override fun initData() {
        loadArticleList("0")
    }

    private fun loadArticleList(page: String) {
        apiHelper.getRetrofitApiHolder()
                .loadArticleList(page, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultObserver<ArticleResponse>() {
                    override fun onSuccess(result: ArticleResponse) {
                        adapter.setData(result.data.datas)
                    }

                    override fun onError(e: Throwable?, result: ArticleResponse?) {
                    }

                })

    }
}