package view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import cn.xunger.and.wanandroid.`interface`.OnItemClickListener

/**
 * Created on 2018/4/11.
 *
 */
abstract class CommonAdapter<T, VH : RecyclerView.ViewHolder>(context: Context) : RecyclerView.Adapter<VH>() {

    protected val data: ArrayList<T> = arrayListOf()
    protected val inflater = LayoutInflater.from(context)!!
    lateinit var onItemClickListener: OnItemClickListener<T>

    fun setData(data: ArrayList<T>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    protected fun getItem(position: Int): T {
        return data[position]
    }
}