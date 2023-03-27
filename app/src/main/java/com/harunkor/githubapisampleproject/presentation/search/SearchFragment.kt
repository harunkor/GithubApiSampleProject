package com.harunkor.githubapisampleproject.presentation.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.harunkor.githubapisampleproject.R
import com.harunkor.githubapisampleproject.databinding.FragmentUsersBinding
import com.harunkor.githubapisampleproject.presentation.base.BaseFragment
import com.harunkor.githubapisampleproject.presentation.user.UserViewModel

class SearchFragment : BaseFragment<FragmentUsersBinding, UserViewModel>() {

    private val userViewModel: UserViewModel by activityViewModels()

    override fun getLayoutId() =  R.layout.fragment_search

    override fun getViewModel(): UserViewModel  = userViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}