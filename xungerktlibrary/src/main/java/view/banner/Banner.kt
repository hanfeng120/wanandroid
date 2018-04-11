package view.banner

import android.content.Context
import android.os.Handler
import android.os.Message
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import cn.xunger.and.xungerktlibrary.R
import java.lang.ref.WeakReference
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * Created on 2018/4/10.
 *
 */
class Banner<T> : RelativeLayout {

    private val BANNER_SIZE_COEFFICIENT = 1000
    private val BANNER_AUTOPLAY_INTERVAL = 4000L
    private var inflater: LayoutInflater
    private lateinit var viewPager: ViewPager
    private val data = arrayListOf<T>()
    private var bannerPagerAdapter = BannerPagerAdapter()
    lateinit var bannerAdapter: BannerAdapter<T>
    private var executor: ScheduledExecutorService? = null
    private val bannerHandler = BannerHandler(this)

    private var currentPosition = 0

    private var isPlaying = false

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

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                currentPosition = position
            }

        })
    }

    private fun createImageView(): ImageView {
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        return imageView
    }

    fun setBannerData(data: ArrayList<T>) {
        this.data.clear()
        this.data.addAll(data)
    }

    fun notifyDataSetChanged() {
        bannerPagerAdapter.notifyDataSetChanged()
        setCurrentItem()
        goScroll()
    }

    private fun goScroll() {
        if (isPlaying) {
            return
        }
        executor = Executors.newSingleThreadScheduledExecutor()
        executor?.scheduleAtFixedRate({
            bannerHandler.obtainMessage().sendToTarget()
        }, BANNER_AUTOPLAY_INTERVAL, BANNER_AUTOPLAY_INTERVAL, TimeUnit.MILLISECONDS)
        isPlaying = true
    }

    private fun pauseScroll() {
        if (executor != null) {
            executor?.shutdown()
            executor = null
        }
        isPlaying = false
    }

    private fun nextPage() {
        if (currentPosition++ == bannerPagerAdapter.getPageCount()) {
            currentPosition = 0
        }
        setCurrentItem()
    }

    private fun setCurrentItem() {
        viewPager.setCurrentItem(currentPosition, true)
    }

    private inner class BannerPagerAdapter : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return getPageCount()
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = createImageView()
            bannerAdapter.bindImageView(imageView, data[position % data.size])
            container.addView(imageView)
            return imageView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }

        fun getPageCount(): Int {
            return if (data.size <= 1) {
                data.size
            } else {
                data.size * BANNER_SIZE_COEFFICIENT
            }
        }

    }

    private class BannerHandler<T>(banner: Banner<T>) : Handler() {
        private val weakReference = WeakReference(banner)

        override fun handleMessage(msg: Message?) {
            if (weakReference.get() != null) {
                weakReference.get()!!.nextPage()
            }
            super.handleMessage(msg)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            pauseScroll()
        } else if (ev?.action == MotionEvent.ACTION_UP || ev?.action == MotionEvent.ACTION_CANCEL) {
            goScroll()
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onVisibilityChanged(changedView: View?, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (View.GONE == visibility) {
            pauseScroll()
        } else {
            goScroll()
        }
    }

}