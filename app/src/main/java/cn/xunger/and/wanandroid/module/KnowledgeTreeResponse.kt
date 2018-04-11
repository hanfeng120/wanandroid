package cn.xunger.and.wanandroid.module

/**
 * Created on 2018/4/11.
 *
 */
class KnowledgeTreeResponse : BaseResponse() {

    lateinit var data: ArrayList<KnowledgeTree>

    class KnowledgeTree {
        lateinit var children: ArrayList<KnowledgeTree>
        var courseId = 0
        var id = 0
        var order = 0
        var parentChapterId = 0
        var visible = 0
        lateinit var name: String
    }

}