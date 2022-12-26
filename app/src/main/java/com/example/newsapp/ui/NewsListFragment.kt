package com.example.newsapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsItemAdapter
import com.example.newsapp.databinding.FragmentNewsListBinding
import com.example.newsapp.viewmodels.MainViewModel

class NewsListFragment : Fragment() {


    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = NewsItemAdapter(
            NewsItemAdapter.ArticleListener { article ->
                viewModel.onArticleClicked(article)
                findNavController().navigate(R.id.action_newsListFragment_to_newsDetailFragment)
            })
    }

    @Deprecated("Deprecated in Java",
        ReplaceWith("menuInflater.inflate(R.menu.menu, menu)", "com.example.newsapp.R"))
    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
    }


    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.refreshDataFromRepository(item.title.toString())
        return super.onContextItemSelected(item)
    }
}