package cn.xunger.and.xungerktlibrary.utils

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created on 2018/3/2.
 *
 */
class ImageLoader() {

    companion object {
        fun loadImage(activity: Context, imageView: ImageView, url: String) {
            Glide.with(activity)
                    .load(url)
                    .into(imageView)
        }

        fun loadImage(fragment: Fragment, imageView: ImageView, url: String) {
            Glide.with(fragment)
                    .load(url)
                    .into(imageView)
        }
    }

}