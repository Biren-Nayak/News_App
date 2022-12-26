package com.example.newsapp.adapters

import android.view.View.*
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.R
import com.example.newsapp.models.Article
import com.example.newsapp.utils.Constants
import com.example.newsapp.utils.Constants.DATA
import com.example.newsapp.utils.Constants.FETCH_STATUS
import com.example.newsapp.utils.Constants.FetchStatus.*
import com.example.newsapp.utils.Constants.HTTPS
import com.example.newsapp.utils.Constants.IMG_URL


@BindingAdapter(DATA)
fun bindData(recyclerView: RecyclerView, data: List<Article>?) {
    val adapter = recyclerView.adapter as NewsItemAdapter
    adapter.submitList(data)
}


@BindingAdapter(IMG_URL)
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme(HTTPS).build()
        imageView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}


@BindingAdapter(FETCH_STATUS)
fun fetchStatus(imageView: ImageView, status: Constants.FetchStatus) {
    when (status) {
        SUCCESS -> imageView.visibility = GONE

        LOADING -> {
            imageView.visibility = INVISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }

        ERROR -> {
            imageView.visibility = INVISIBLE
            imageView.setImageResource(R.drawable.ic_error)
        }
    }
}
