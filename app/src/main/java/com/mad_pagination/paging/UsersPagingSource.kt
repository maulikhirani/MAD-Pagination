package com.mad_pagination.paging

import androidx.paging.PagingSource
import com.mad_pagination.model.User
import com.mad_pagination.network.ApiService

class UsersPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getUsers(nextPageNumber)
            LoadResult.Page(
                response,
                null,
                nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}