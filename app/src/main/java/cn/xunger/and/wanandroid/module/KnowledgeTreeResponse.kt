package cn.xunger.and.wanandroid.module

import android.os.Parcel
import android.os.Parcelable

/**
 * Created on 2018/4/11.
 *
 */
class KnowledgeTreeResponse : BaseResponse() {

    lateinit var data: ArrayList<KnowledgeTree>

    class KnowledgeTree() : Parcelable {

        var children: ArrayList<KnowledgeTree> = arrayListOf()
        var courseId = 0
        var id = 0
        var order = 0
        var parentChapterId = 0
        var visible = 0
        lateinit var name: String

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeList(children)
            dest?.writeInt(courseId)
            dest?.writeInt(id)
            dest?.writeInt(order)
            dest?.writeInt(parentChapterId)
            dest?.writeInt(visible)
            dest?.writeString(name)
        }

        override fun describeContents(): Int {
            return 0
        }

        constructor(parcel: Parcel) : this() {
            parcel.readList(children, KnowledgeTree::class.java.classLoader)
            courseId = parcel.readInt()
            id = parcel.readInt()
            order = parcel.readInt()
            parentChapterId = parcel.readInt()
            visible = parcel.readInt()
            var name = parcel.readString()
            this.name = if (name == null) {
                ""
            } else {
                name
            }
        }

        companion object CREATOR : Parcelable.Creator<KnowledgeTree> {
            override fun createFromParcel(parcel: Parcel): KnowledgeTree {
                return KnowledgeTree(parcel)
            }

            override fun newArray(size: Int): Array<KnowledgeTree?> {
                return arrayOfNulls(size)
            }
        }
    }

}