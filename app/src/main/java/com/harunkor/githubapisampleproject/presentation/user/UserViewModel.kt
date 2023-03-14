package com.harunkor.githubapisampleproject.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.harunkor.githubapisampleproject.domain.model.user.SearchUser
import com.harunkor.githubapisampleproject.domain.model.user.User
import com.harunkor.githubapisampleproject.domain.repository.user.UserRepository
import com.harunkor.githubapisampleproject.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): BaseViewModel() {

    private val _userList: MutableLiveData<List<User>?> = MutableLiveData()
    val userList: LiveData<List<User>?> = _userList

    private val _detailUser: MutableLiveData<User?> = MutableLiveData()
    val detailUser: LiveData<User?> = _detailUser

    private val _searchUser: MutableLiveData<SearchUser?> = MutableLiveData()
    val searchUser: LiveData<SearchUser?> = _searchUser

    fun getUserList(){
        launchViewModelScope(userRepository.getUserList()){ response ->
            response.body().let {
                _userList.value = it
            }
        }
    }

    fun getDetailUser(username: String){
        launchViewModelScope(userRepository.getDetailUser(username)){ response ->
            response.body().let {
                _detailUser.value = it
            }
        }
    }

    fun getSearchUser(query: String){
        launchViewModelScope(userRepository.getSearchUser(query)){ response ->
            response.body().let {
                _searchUser.value = it
            }
        }
    }

}