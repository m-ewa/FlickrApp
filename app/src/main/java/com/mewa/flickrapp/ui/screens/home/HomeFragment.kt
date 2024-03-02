package com.mewa.flickrapp.ui.screens.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mewa.flickrapp.R
import com.mewa.flickrapp.databinding.FragmentHomeBinding
import com.mewa.flickrapp.ui.item.ItemAdapter
import com.mewa.flickrapp.ui.item.ItemUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null

    private val binding: FragmentHomeBinding
        get() = _binding!!

    private val clickListener: (item: ItemUiState) -> Unit = { item ->
        onClickAction(item.link)
    }

    private val catAdapter: ItemAdapter by lazy {
        ItemAdapter(
            clickListener = { clickListener(it) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        subscribeUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUi() = with(binding) {
        val columnCount = resources.getInteger(R.integer.grid_column_count)

        recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                columnCount,
                StaggeredGridLayoutManager.VERTICAL
            )
            adapter = catAdapter
        }
    }

    private fun subscribeUi() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.homeUiState.collect { uiState ->
                        when (uiState) {
                            is HomeUiState.Loading -> onLoading()
                            is HomeUiState.Success -> onSuccess(uiState)
                            is HomeUiState.Failure -> onFailure(uiState)
                        }
                    }
                }
            }
        }
    }

    private fun onLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun onSuccess(homeUiState: HomeUiState.Success) {
        binding.progressBar.visibility = View.GONE
        catAdapter.submitList(homeUiState.cats)
    }

    private fun onFailure(homeUiState: HomeUiState.Failure) {
        binding.progressBar.visibility = View.GONE

        val errorTitle = getString(R.string.error_on_items_fetch_title)
        val commonMessage = getString(R.string.error_on_items_fetch_common_message)
        val neutralButtonLabel = getString(R.string.error_on_items_fetch_retry)
        val negativeButtonLabel = getString(R.string.error_on_items_fetch_exit)

        AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setNeutralButton(neutralButtonLabel) { _, _ ->
                viewModel.downloadCats()
            }
            .setNegativeButton(negativeButtonLabel) { _, _ ->
                lifecycleScope.launch {
                    lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                        requireActivity().finishAffinity()
                    }
                }
            }
            .setTitle(errorTitle)
            .setMessage(homeUiState.errorMessage ?: commonMessage)
            .create()
            .show()
    }

    private fun onClickAction(link: String?) {
        if (!link.isNullOrEmpty()) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(browserIntent)
        } else {
            val message = getString(R.string.error_no_link_available)
            Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
        }
    }
}