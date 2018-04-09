package cn.xunger.and.wanandroid.mainpage.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import cn.xunger.and.wanandroid.R
import cn.xunger.and.wanandroid.`interface`.OnItemClickListener
import cn.xunger.and.wanandroid.module.HomeArticleResponse

/**
 * Created on 2018/4/9.
 *
 */
class ArticleAdapter(context: Context) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    var inflater: LayoutInflater = LayoutInflater.from(context)
    var articles = arrayListOf<HomeArticleResponse.HomeArticle>()
    lateinit var onItemClickListener: OnItemClickListener

    fun setData(data: ArrayList<HomeArticleResponse.HomeArticle>) {
        articles.clear()
        articles.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val rootView = inflater.inflate(R.layout.item_home_article, parent, false)
        return ArticleViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    private fun getItem(position: Int): HomeArticleResponse.HomeArticle {
        return articles[position]
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.llContent.setOnClickListener {
            onItemClickListener.onItemClick(position, it)
        }
        holder.tvTitle.text = article.title
        holder.tvAuthor.text = article.author
        holder.tvTime.text = article.niceDate
    }

    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val llContent = view.findViewById<LinearLayout>(R.id.ll_content)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvAuthor: TextView = view.findViewById(R.id.tv_author)
        val tvTime: TextView = view.findViewById(R.id.tv_time)
        lateinit var ivCollect: ImageView

    }

}