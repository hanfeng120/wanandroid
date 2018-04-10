package view.banner

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import cn.xunger.and.xungerktlibrary.R

/**
 * Created on 2018/4/10.
 *
 */
class Banner<T> : RelativeLayout {

    private var inflater: LayoutInflater
    private lateinit var viewPager: ViewPager
    private val data = arrayListOf<T>()
    private var bannerPagerAdapter = BannerPagerAdapter()
    lateinit var bannerAdapter: BannerAdapter<T>

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflater = LayoutInflater.from(context)
        initView()
    }

    private fun initView() {
        inflater.inflate(R.layout.layout_banner, this)
        viewPager = findViewById(R.id.view_pager)
        initViewPager()
    }

    private fun initViewPager() {
        viewPager.adapter = bannerPagerAdapter
    }

    private fun createImageView(): ImageView {
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        return imageView
    }

    fun setBannerData(data: ArrayList<T>) {
        this.data.clear()
        this.data.addAll(data)
        bannerPagerAdapter.notifyDataSetChanged()
    }

    private inner class BannerPagerAdapter : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return data.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = createImageView()
            bannerAdapter.bindImageView(imageView, data[position])
            container.addView(imageView)
            return imageView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }

    }

}