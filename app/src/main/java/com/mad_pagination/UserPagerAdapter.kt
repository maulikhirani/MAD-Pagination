package com.mad_pagination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mad_pagination.databinding.ListItemUserBinding
import com.mad_pagination.model.User

class UserPagerAdapter(diffCallback: DiffUtil.ItemCallback<User>):
    PagingDataAdapter<User, UserPagerAdapter.UserViewHolder>(diffCallback) {


    inner class UserViewHolder(val binding: ListItemUserBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()
        }

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ListItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

}

object UserComparator : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        // Id is unique.
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}