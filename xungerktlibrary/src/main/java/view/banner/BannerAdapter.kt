package view.banner

import android.widget.ImageView

/**
 * Created on 2018/4/10.
 *
 */
abstract class BannerAdapter<in T> {

    abstract fun bindImageView(imageView: ImageView, data: T)

}