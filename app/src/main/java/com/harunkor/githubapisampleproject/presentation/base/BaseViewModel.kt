package com.harunkor.githubapisampleproject.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harunkor.githubapisampleproject.domain.model.base.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Response

abstract class BaseViewModel: ViewModel() {

    private val _showProgressLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val showProgressLiveData: LiveData<Boolean> = _showProgressLiveData

    private val _showErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val showErrorLiveData: LiveData<String> = _showErrorLiveData


    fun <T> launchViewModelScope(
        block: Flow<Response<T>>,
        isLoading: Boolean = true,
        dismissLoading: Boolean = true,
        errorMessage: ((String) -> Unit)? = null,
        collectData: ((Response<T>) -> Unit),
    ) {
        viewModelScope.launch {
            block.onStart {
                _showProgressLiveData.value = isLoading
            }.catch { exception ->
                _showErrorLiveData.value = exception.message
            }.collect {
                if (it.isSuccessful) {
                    collectData(it)
                } else {
                    //todo
                    _showErrorLiveData.value = it.message()
                    errorMessage?.invoke(it.message())
                }

                if (dismissLoading) {
                    _showProgressLiveData.value = false
                }
            }
        }
    }


}