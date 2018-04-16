package cn.xunger.and.wanandroid.knowledge

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.framework.CommonActivity
import cn.xunger.and.wanandroid.module.KnowledgeTreeResponse
import cn.xunger.and.xungerktlibrary.utils.getParameter
import kotlinx.android.synthetic.main.activity_knowledge_tree.*

class KnowledgeTreeActivity : CommonActivity() {

    private lateinit var data: KnowledgeTreeResponse.KnowledgeTree
    private var pagerAdapter = KnowledgeTreePagerAdapter()
    private val treeViewMap: HashMap<Int, KnowledgeTreeViewGroup> = hashMapOf()

    override fun getLayoutResId(): Int {
        return R.layout.activity_knowledge_tree
    }

    override fun initParams(bundle: Bundle) {
        super.initParams(bundle)
        data = getParameter()
    }

    override fun initView() {
        title = data.name
        tab_layout.setupWithViewPager(view_pager)
        view_pager.adapter = pagerAdapter
    }

    override fun initData() {

    }

    private fun createTreeView(position: Int): KnowledgeTreeViewGroup {
        var treeView = treeViewMap[position]
        return if (treeView == null) {
            val knowledgeTreeView = KnowledgeTreeViewGroup(getContext(), data.children[position].id.toString())
            knowledgeTreeView.createView(null)
            knowledgeTreeView.init()
            treeViewMap[position] = knowledgeTreeView
            knowledgeTreeView
        } else {
            treeView
        }
    }

    inner class KnowledgeTreePagerAdapter : PagerAdapter() {

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return data.children.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val viewGroup = createTreeView(position)
            container.addView(viewGroup.rootView)
            return viewGroup.rootView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return data.children[position].name
        }

    }

}
