package cn.xunger.and.wanandroid.mainpage.knowledgetree

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.module.KnowledgeTreeResponse
import view.CommonAdapter

/**
 * Created on 2018/4/11.
 *
 */
class KnowledgeTreeAdapter(context: Context) :
        CommonAdapter<KnowledgeTreeResponse.KnowledgeTree, KnowledgeTreeAdapter.KnowledgeTreeViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnowledgeTreeViewHolder {
        val itemView = inflater.inflate(R.layout.item_knowledge_tree, parent, false)
        return KnowledgeTreeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: KnowledgeTreeViewHolder, position: Int) {
        val itemData = getItem(position)
        holder.tvTitle.text = itemData.name
        holder.tvSubTitle.text = itemData.children.joinToString(" / ") {
            it.name
        }
        holder.llContent.setOnClickListener {
            onItemClickListener.onItemClick(position, it)
        }
    }

    class KnowledgeTreeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val llContent = view.findViewById<LinearLayout>(R.id.ll_content)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val tvSubTitle = view.findViewById<TextView>(R.id.tv_subtitle)

    }

}