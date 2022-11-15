package com.example.newsapp.ui

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.databinding.FragmentNewsDetailBinding
import com.example.newsapp.ui.viewmodels.MainViewModel
import com.example.newsapp.ui.viewmodels.MainViewModelFactory


class NewsDetailFragment : Fragment() {

    private lateinit var binding: FragmentNewsDetailBinding

    private val viewModel: MainViewModel by activityViewModels {
        activity?.application?.let { MainViewModelFactory(it) }!!

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.readMore.setOnClickListener {
            viewModel.selectedArticle.value?.let {
                val uri = Uri.parse(it.url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                requireContext().startActivity(intent)
            }
        }
    }

}