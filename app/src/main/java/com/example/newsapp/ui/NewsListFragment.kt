package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsItemAdapter
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.viewmodels.MainViewModel
import com.example.newsapp.viewmodels.MainViewModelFactory

class NewsListFragment : Fragment() {


    private val viewModel: MainViewModel by activityViewModels {
        activity?.application?.let { MainViewModelFactory(it) }!!
    }


    private lateinit var binding: FragmentNewsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = NewsItemAdapter( NewsItemAdapter.ArticleListener { article ->
            viewModel.onArticleClicked(article)
            findNavController().navigate(R.id.action_newsListFragment_to_newsDetailFragment)
        })
    }

}