package cn.xunger.and.wanandroid.mainpage

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.`interface`.OnItemClickListener
import cn.xunger.and.wanandroid.framework.CommonFragment
import cn.xunger.and.wanandroid.mainpage.knowledgetree.KnowledgeTreeAdapter
import cn.xunger.and.wanandroid.module.KnowledgeTreeResponse
import cn.xunger.and.xungerktlibrary.net.DefaultObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_knowledge.*

/**
 * Created on 2018/4/3.
 *
 */
class KnowledgeTreeFragment : CommonFragment() {

    private lateinit var adapter: KnowledgeTreeAdapter
    private lateinit var treeResponse: KnowledgeTreeResponse

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_knowledge, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        swipe_refresh.setOnRefreshListener {
            initData()
        }
        adapter = KnowledgeTreeAdapter(context!!)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.isNestedScrollingEnabled = false
        recycler_view.setHasFixedSize(false)
        adapter.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int, view: View) {
                Toast.makeText(context, "${position}号位置被点击了", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun initData() {
        apiHelper.getRetrofitApiHolder()
                .loadKnowledgeTreeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultObserver<KnowledgeTreeResponse>() {
                    override fun onSuccess(result: KnowledgeTreeResponse) {
                        swipe_refresh.isRefreshing = false
                        treeResponse = result
                        refreshData()
                    }

                    override fun onError(e: Throwable?, result: KnowledgeTreeResponse?) {
                        swipe_refresh.isRefreshing = false
                    }

                })
    }

    private fun refreshData() {
        adapter.setData(treeResponse.data)
    }

}