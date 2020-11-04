package com.mad_pagination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mad_pagination.databinding.HomeFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private val pagingAdapter = UserPagerAdapter(UserComparator)
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvUsers.adapter = pagingAdapter

        pagingAdapter.withLoadStateHeaderAndFooter(
            UserLoadingAdapter(pagingAdapter::retry),
            UserLoadingAdapter(pagingAdapter::retry)
        )

        lifecycleScope.launch {
            homeViewModel.flow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

}