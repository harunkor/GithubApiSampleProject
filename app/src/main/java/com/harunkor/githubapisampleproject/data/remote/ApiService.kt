package com.harunkor.githubapisampleproject.data.remote


import com.harunkor.githubapisampleproject.domain.model.base.ApiResponse
import com.harunkor.githubapisampleproject.domain.model.user.SearchUser
import com.harunkor.githubapisampleproject.domain.model.user.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUserList(): Response<List<User>>

    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") username: String): Response<User>

    @GET("search/users")
    suspend fun getSearchUser(@Query("q") query: String): Response<SearchUser>

}