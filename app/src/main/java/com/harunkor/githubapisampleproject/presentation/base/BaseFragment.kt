package com.harunkor.githubapisampleproject.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.harunkor.githubapisampleproject.BR
import com.harunkor.githubapisampleproject.presentation.clicklistener.BackClickListener
import com.harunkor.githubapisampleproject.extension.observe
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel> : Fragment(), BackClickListener {

    @Inject
    lateinit var destination: Destination

    abstract fun getLayoutId(): Int

    lateinit var binding: B

    abstract fun getViewModel(): VM

    private fun <T : ViewDataBinding> binding(
        inflater: LayoutInflater,
        @LayoutRes resId: Int,
        container: ViewGroup?
    ): T = DataBindingUtil.inflate(inflater, resId, container, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(
            inflater,
            getLayoutId(),
            container
        )
        binding.setVariable(BR.backClickListener, this@BaseFragment)

        observe(getViewModel().showProgressLiveData) {
            if (it) {
                (requireActivity() as BaseActivity<*, *>).showLoading(childFragmentManager)
            } else {
                (requireActivity() as BaseActivity<*, *>).hideLoading(childFragmentManager)
            }
        }

        return binding.root
    }

    override fun backButtonClick() {
       requireActivity().onBackPressedDispatcher.onBackPressed()
    }

}