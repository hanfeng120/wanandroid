package cn.xunger.and.wanandroid.mainpage.home

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.common.Constants
import cn.xunger.and.wanandroid.module.HomeBannerResponse
import cn.xunger.and.xungerktlibrary.utils.ImageLoader

/**
 * Created on 2018/4/9.
 *
 */
class BannerPagerAdapter(val context: Context) : PagerAdapter() {

    private val layoutInflater = LayoutInflater.from(context)
    private val banners: ArrayList<HomeBannerResponse.HomeBanner> = arrayListOf()

    fun setData(data: ArrayList<HomeBannerResponse.HomeBanner>) {
        banners.clear()
        banners.addAll(data)
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return banners.size * Constants.HOME_BANNER_SIZE_COEFFICIENT * 2
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rootView = layoutInflater.inflate(R.layout.item_home_banner, container, false) as ImageView
        val homeBanner = banners[position % banners.size]
        ImageLoader.loadImage(context, rootView, homeBanner.imagePath)
        container.addView(rootView)
        return rootView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

}