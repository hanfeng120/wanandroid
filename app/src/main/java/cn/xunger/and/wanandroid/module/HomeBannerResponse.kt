package cn.xunger.and.wanandroid.module

/**
 * Created on 2018/4/9.
 *
 */
class HomeBannerResponse : BaseResponse() {

    lateinit var data: ArrayList<HomeBanner>

    class HomeBanner {
        var isVisible: Int = 0
        var order: Int = 0
        var type: Int = 0
        lateinit var desc: String
        lateinit var imagePath: String
        lateinit var title: String
        lateinit var url: String
    }

}
