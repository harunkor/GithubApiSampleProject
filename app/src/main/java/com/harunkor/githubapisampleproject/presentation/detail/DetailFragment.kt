package com.harunkor.githubapisampleproject.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.harunkor.githubapisampleproject.BR
import com.harunkor.githubapisampleproject.R
import com.harunkor.githubapisampleproject.databinding.FragmentDetailBinding
import com.harunkor.githubapisampleproject.databinding.FragmentUsersBinding
import com.harunkor.githubapisampleproject.presentation.base.BaseFragment
import com.harunkor.githubapisampleproject.presentation.extension.observe
import com.harunkor.githubapisampleproject.presentation.user.UserViewModel

class DetailFragment : BaseFragment<FragmentDetailBinding, UserViewModel>() {

    val args: DetailFragmentArgs by navArgs()

    private val userViewModel: UserViewModel by activityViewModels()

    override fun getLayoutId() =  R.layout.fragment_detail

    override fun getViewModel(): UserViewModel  = userViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initObservers()
    }

    private fun initData() = with(userViewModel){
        getDetailUser(args.userName)
    }

    private fun initObservers() = with(userViewModel) {
        observe(detailUser){ user ->
            binding.setVariable(BR.item,user)
        }
    }


}