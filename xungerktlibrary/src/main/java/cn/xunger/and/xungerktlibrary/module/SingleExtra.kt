package cn.xunger.and.xungerktlibrary.module

import android.os.Parcel
import android.os.Parcelable

/**
 * Created on 2018/4/12.
 *
 */
class SingleExtra() : Parcelable {

    constructor(parcel: Parcel) : this() {
        this.value = parcel.readString()
    }

    constructor(value: String) : this() {
        this.value = value
    }

    lateinit var value: String

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SingleExtra> {
        override fun createFromParcel(parcel: Parcel): SingleExtra {
            return SingleExtra(parcel)
        }

        override fun newArray(size: Int): Array<SingleExtra?> {
            return arrayOfNulls(size)
        }
    }

}