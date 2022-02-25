package com.example.feature.ui.details

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.base.BaseFragment
import com.example.feature.databinding.FragmentDetailBinding
import com.example.feature.ui.contract.DetailsContract
import com.example.feature.ui.contract.MainContract
import com.example.feature.ui.main.AuthorsAdapter
import com.example.feature.ui.main.MainFragmentDirections
import com.example.feature.ui.vm.DetailsViewModel
import com.example.feature.ui.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * Detail Fragment
 */
@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val viewModel: DetailsViewModel by viewModels()
    private val adapter: PostsAdapter by lazy {
        PostsAdapter()
    }

    private val args : DetailFragmentArgs by navArgs()


    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.rvPosts.adapter = adapter


        args.author?.let { author ->
            binding.author = author

            viewModel.setEvent(DetailsContract.Event.OnFetchPosts(author.id))

        }
        initObservers()
    }

    /**
     * Initialize Observers
     */
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {

                    when (val state = it.postsState) {
                        is DetailsContract.PostsState.Idle -> {
                            binding.loadingPb.isVisible = false
                        }
                        is DetailsContract.PostsState.Loading -> {
                            binding.loadingPb.isVisible = true
                        }
                        is DetailsContract.PostsState.Success -> {
                            val data = state.postList
                            adapter.submitList(data)
                            binding.loadingPb.isVisible = false
                        }
                    }

                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is DetailsContract.Effect.ShowError -> {
                            val msg = it.message
                            showErrorBanner(msg)

                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        //overcome memory leaks
        binding.rvPosts.adapter = null
        super.onDestroyView()
    }

}