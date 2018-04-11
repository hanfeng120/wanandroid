package cn.xunger.and.wanandroid.mainpage.knowledgetree

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.module.KnowledgeTreeResponse

/**
 * Created on 2018/4/11.
 *
 */
class KnowledgeTreeAdapter(context: Context) : RecyclerView.Adapter<KnowledgeTreeAdapter.KnowledgeTreeViewHolder>() {

    private val data: ArrayList<KnowledgeTreeResponse.KnowledgeTree> = arrayListOf()
    private val inflater = LayoutInflater.from(context)

    fun setData(data: ArrayList<KnowledgeTreeResponse.KnowledgeTree>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnowledgeTreeViewHolder {
        val itemView = inflater.inflate(R.layout.item_knowledge_tree, parent, false)
        return KnowledgeTreeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(position: Int): KnowledgeTreeResponse.KnowledgeTree {
        return data[position]
    }

    override fun onBindViewHolder(holder: KnowledgeTreeViewHolder, position: Int) {
        val itemData = getItem(position)
        holder.tvTitle.text = itemData.name
        holder.tvSubTitle.text = itemData.children.joinToString(" / ") {
            it.name
        }
        holder.llContent.setOnClickListener {

        }
    }

    class KnowledgeTreeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val llContent = view.findViewById<LinearLayout>(R.id.ll_content)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val tvSubTitle = view.findViewById<TextView>(R.id.tv_subtitle)

    }

}