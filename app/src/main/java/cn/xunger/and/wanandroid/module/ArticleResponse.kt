package cn.xunger.and.wanandroid.module

/**
 * Created on 2018/4/4.
 *
 */
class ArticleResponse : BaseResponse() {

    lateinit var data: HomeArticleData

    class HomeArticleData {

        var curPage = 0
        lateinit var datas: ArrayList<Article>

    }

    class Article {
        lateinit var apkLink: String
        lateinit var author: String
        lateinit var chapterId: String
        lateinit var chapterName: String
        lateinit var collect: String
        lateinit var courseId: String
        lateinit var desc: String
        lateinit var envelopePic: String
        lateinit var fresh: String
        lateinit var id: String
        lateinit var link: String
        lateinit var niceDate: String
        lateinit var origin: String
        lateinit var projectLink: String
        lateinit var publishTime: String
        lateinit var superChapterId: String
        lateinit var superChapterName: String
        lateinit var tags: ArrayList<ArticleTag>
        lateinit var title: String
        lateinit var type: String
        lateinit var visible: String
        var zan = 0
    }

    class ArticleTag {
        lateinit var name: String
        lateinit var url: String
    }

}