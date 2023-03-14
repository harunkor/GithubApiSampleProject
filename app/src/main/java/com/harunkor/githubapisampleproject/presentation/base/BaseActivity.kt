package com.harunkor.githubapisampleproject.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.harunkor.githubapisampleproject.presentation.extension.observe
import com.harunkor.githubapisampleproject.utils.ErrorDialog
import com.harunkor.githubapisampleproject.utils.LoadingDialog

abstract class BaseActivity <B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(){

    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): VM

    val binding: B by binding(getLayoutId())

    private fun <T : ViewDataBinding> binding(
        @LayoutRes resId: Int
    ): Lazy<T> = lazy { DataBindingUtil.setContentView(this, resId) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        initBaseObserver()
    }

    private fun initBaseObserver(){

        observe(getViewModel().showErrorLiveData) {
                ErrorDialog(it).show(supportFragmentManager,ERROR)
        }

        observe(getViewModel().showProgressLiveData) {
            if (it) {
                showLoading(supportFragmentManager)
            } else {
                hideLoading(supportFragmentManager)
            }
        }
    }

    private fun showLoading(fm: FragmentManager) {
        LoadingDialog().apply {
            isCancelable = true
            show(fm, LOADING)
        }
    }

    private fun hideLoading(fm: FragmentManager) {
        fm.fragments.filterIsInstance<LoadingDialog>().forEach {
            it.dismiss()
        }
    }

    companion object {
        private const val LOADING = "Loading"
        private const val ERROR = "Error"
    }

}