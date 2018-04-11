package cn.xunger.and.wanandroid.mainpage

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.`interface`.OnItemClickListener
import cn.xunger.and.wanandroid.framework.CommonFragment
import cn.xunger.and.wanandroid.mainpage.home.ArticleAdapter
import cn.xunger.and.wanandroid.module.HomeArticleResponse
import cn.xunger.and.wanandroid.module.HomeBannerResponse
import cn.xunger.and.wanandroid.module.Page
import cn.xunger.and.wanandroid.web.WebViewActivity
import cn.xunger.and.xungerktlibrary.net.DefaultObserver
import cn.xunger.and.xungerktlibrary.utils.ImageLoader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import view.banner.Banner
import view.banner.BannerAdapter

/**
 * Created on 2018/4/3.
 *
 */
class HomeFragment : CommonFragment() {

    private val TAG = "HomeFragment"
    private val page = Page()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var banner: Banner<HomeBannerResponse.HomeBanner>
    private lateinit var recyclerView: RecyclerView
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var articleResponse: HomeArticleResponse
    private lateinit var bannerResponse: HomeBannerResponse

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        swipeRefresh = rootView.findViewById(R.id.swipe_refresh)
        banner = rootView.findViewById(R.id.banner)
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
        initViewPager()
        initRecyclerView()
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
                        refreshArticleList()
                    }

                    override fun onError(e: Throwable?, result: HomeArticleResponse?) {
                        swipeRefresh.isRefreshing = false
                        Log.d(TAG, "onError: " + e.toString())
                    }

                })
        apiHelper.getRetrofitApiHolder()
                .loadHomeBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultObserver<HomeBannerResponse>() {
                    override fun onSuccess(result: HomeBannerResponse) {
                        bannerResponse = result
                        banner.setBannerData(result.data)
                        banner.notifyDataSetChanged()
                    }

                    override fun onError(e: Throwable?, result: HomeBannerResponse?) {
                        Log.d(TAG, "onError: " + e.toString())
                    }

                })
    }

    private fun initSwipeRefreshLayout() {
        swipeRefresh.setOnRefreshListener {
            page.reset()
            loadData()
        }
    }

    private fun initViewPager() {
        banner.bannerAdapter = object : BannerAdapter<HomeBannerResponse.HomeBanner>() {
            override fun bindImageView(imageView: ImageView, data: HomeBannerResponse.HomeBanner) {
                ImageLoader.loadImage(context!!, imageView, data.imagePath)
            }

        }
    }

    private fun initRecyclerView() {
        articleAdapter = ArticleAdapter(context!!)
        recyclerView.adapter = articleAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isFocusable = false
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setHasFixedSize(false)

        articleAdapter.onItemClickListener = object : OnItemClickListener<HomeArticleResponse.HomeArticle> {
            override fun onItemClick(position: Int, data: HomeArticleResponse.HomeArticle, view: View) {
                WebViewActivity.startNewsDetailActivity(context!!, data.link)
            }

        }
    }

    private fun refreshArticleList() {
        articleAdapter.setData(articleResponse.data.datas)
    }

}