package cn.xunger.and.wanandroid.mainpage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.framework.CommonActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : CommonActivity() {

    private lateinit var pagerAdapter: FragmentPagerAdapter
    private val homeFragment = HomeFragment()
    private val knowledgeFragment = KnowledgeFragment()
    private val userFragment = UserFragment()
    private val fragments = arrayOf(homeFragment, knowledgeFragment, userFragment)
    private val bottomMenuIds = arrayListOf(R.id.home, R.id.knowledge, R.id.user_center)

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initFragmentPagerAdapter()
        view_pager.adapter = pagerAdapter

        bottom_navigation.setOnNavigationItemSelectedListener {
            view_pager.currentItem = bottomMenuIds.indexOf(it.itemId)
            true
        }

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                bottom_navigation.selectedItemId = bottomMenuIds[position]
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    private fun initFragmentPagerAdapter() {
        pagerAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getCount(): Int {
                return fragments.size
            }
        }
    }

    override fun initData() {

    }

    override fun isShowNavigationIcon(): Boolean {
        return false
    }

}
