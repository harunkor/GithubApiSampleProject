package com.harunkor.githubapisampleproject.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.harunkor.githubapisampleproject.BR
import com.harunkor.githubapisampleproject.presentation.clicklistener.BackClickListener
import com.harunkor.githubapisampleproject.extension.observe
import com.harunkor.githubapisampleproject.utils.ErrorDialog
import com.harunkor.githubapisampleproject.utils.LoadingDialog
import javax.inject.Inject

abstract class BaseActivity <B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    BackClickListener {

    @Inject
    lateinit var destination: Destination

    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): VM

    val binding: B by binding(getLayoutId())

    private fun <T : ViewDataBinding> binding(
        @LayoutRes resId: Int
    ): Lazy<T> = lazy { DataBindingUtil.setContentView(this, resId) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.backClickListener, this@BaseActivity)
        }
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

    fun showLoading(fm: FragmentManager) {
        LoadingDialog().apply {
            isCancelable = true
            show(fm, LOADING)
        }
    }

    fun hideLoading(fm: FragmentManager) {
        fm.fragments.filterIsInstance<LoadingDialog>().forEach {
            it.dismiss()
        }
    }

    override fun backButtonClick() {
        onBackPressedDispatcher.onBackPressed()
    }

    companion object {
        private const val LOADING = "Loading"
        private const val ERROR = "Error"
    }

}