package cn.xunger.and.wanandroid.module

/**
 * Created on 2018/4/4.
 *
 */
class Page() {

    var unit = 20
    var page = 0

    constructor(unit: Int) : this() {
        this.unit = unit
    }

    fun up() {
        if (page > 0) {
            page--
        } else {
            page = 0
        }
    }

    fun next() {
        page++
    }

    fun reset() {
        page = 0
    }

}