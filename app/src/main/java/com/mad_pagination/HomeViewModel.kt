package com.mad_pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mad_pagination.network.NetworkManager
import com.mad_pagination.paging.UsersPagingSource

class HomeViewModel : ViewModel() {

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 5)
    ) {
        UsersPagingSource(NetworkManager.apiService)
    }.flow
        .cachedIn(viewModelScope)

}