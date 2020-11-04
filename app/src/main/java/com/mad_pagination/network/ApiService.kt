package com.mad_pagination.network

import com.mad_pagination.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/v1/users")
    suspend fun getUsers(
        @Query("page") pageNo: Int,
        @Query("limit") limit: Int = 5
    ): List<User>

}