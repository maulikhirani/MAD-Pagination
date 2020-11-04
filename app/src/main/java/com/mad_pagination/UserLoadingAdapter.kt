package com.mad_pagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mad_pagination.databinding.ListItemLoadingBinding

class UserLoadingAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<UserLoadingAdapter.UserLoadingViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): UserLoadingViewHolder {
        return UserLoadingViewHolder(
            ListItemLoadingBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), retry
        )
    }

    override fun onBindViewHolder(holder: UserLoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class UserLoadingViewHolder(
        val binding: ListItemLoadingBinding,
        retry: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetry.setOnClickListener { retry() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.progressCircular.visibility = View.GONE
                binding.btnRetry.visibility = View.VISIBLE
            } else if (loadState is LoadState.Loading) {
                binding.progressCircular.visibility = View.VISIBLE
                binding.btnRetry.visibility = View.GONE
            }
        }
    }

}