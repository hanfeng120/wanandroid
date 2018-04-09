package cn.xunger.and.wanandroid.mainpage

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.`interface`.OnItemClickListener
import cn.xunger.and.wanandroid.framework.CommonFragment
import cn.xunger.and.wanandroid.mainpage.home.ArticleAdapter
import cn.xunger.and.wanandroid.module.HomeArticleResponse
import cn.xunger.and.wanandroid.module.Page
import cn.xunger.and.xungerktlibrary.net.DefaultObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created on 2018/4/3.
 *
 */
class HomeFragment : CommonFragment() {

    private val page = Page()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var articleResponse: HomeArticleResponse

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        swipeRefresh = rootView.findViewById(R.id.swipe_refresh)
        recyclerView = rootView.findViewById(R.id.recycler_view)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        loadData()
    }

    private fun initView() {
        initSwipeRefreshLayout()
        initRecyclerView()
    }

    private fun initSwipeRefreshLayout() {
        swipeRefresh.setOnRefreshListener {
            page.reset()
            loadData()
        }
    }

    private fun loadData() {
        apiHelper.getRetrofitApiHolder()
                .loadHomePageList(page.page.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultObserver<HomeArticleResponse>() {
                    override fun onSuccess(result: HomeArticleResponse) {
                        swipeRefresh.isRefreshing = false
                        articleResponse = result
                        refreshData()
                    }

                    override fun onError(e: Throwable?, result: HomeArticleResponse?) {
                        swipeRefresh.isRefreshing = false
                        Log.d("Error", "onError: ")
                    }

                })
    }

    private fun initRecyclerView() {
        articleAdapter = ArticleAdapter(context!!)
        recyclerView.adapter = articleAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setHasFixedSize(false)

        articleAdapter.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int, view: View) {
                Toast.makeText(getContext(), "$position 被点击了", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun refreshData() {
        articleAdapter.setData(articleResponse.data.datas)
    }
}