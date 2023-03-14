package com.harunkor.githubapisampleproject.domain.repository.user

import com.harunkor.githubapisampleproject.data.remote.ApiService
import com.harunkor.githubapisampleproject.di.IoDispatcher
import com.harunkor.githubapisampleproject.domain.model.user.SearchUser
import com.harunkor.githubapisampleproject.domain.model.user.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    fun getUserList(): Flow<Response<MutableList<User>>> {
        return flow {
            val result = apiService.getUserList()
            emit(result)
        }.flowOn(ioDispatcher)
    }

    fun getDetailUser(username: String): Flow<Response<User>> {
        return flow {
            val result = apiService.getDetailUser(username)
            emit(result)
        }.flowOn(ioDispatcher)
    }

    fun getSearchUser(query: String): Flow<Response<SearchUser>> {
        return flow {
            val result = apiService.getSearchUser(query)
            emit(result)
        }.flowOn(ioDispatcher)
    }

}