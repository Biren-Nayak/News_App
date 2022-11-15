package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.adapters.NewsItemAdapter.NewsItemViewHolder
import com.example.newsapp.databinding.NewsListItemBinding
import com.example.newsapp.models.Article

class NewsItemAdapter(private val clickListener: ArticleListener): ListAdapter<Article, NewsItemViewHolder>(DiffCallBack) {

    class NewsItemViewHolder(private var binding: NewsListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: ArticleListener, article: Article){
            binding.article = article
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsItemViewHolder(NewsListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) =
        holder.bind( clickListener, getItem(position) )



    companion object DiffCallBack: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem.title == newItem.title
    }

    class ArticleListener(val clickListener: (article: Article) -> Unit){
        fun onClick(article: Article) = clickListener(article)
    }

}