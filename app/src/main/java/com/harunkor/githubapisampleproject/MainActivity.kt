package com.harunkor.githubapisampleproject

import android.os.Bundle
import androidx.activity.viewModels
import com.harunkor.githubapisampleproject.databinding.ActivityMainBinding
import com.harunkor.githubapisampleproject.presentation.base.BaseActivity
import com.harunkor.githubapisampleproject.presentation.user.UserViewModel
import com.harunkor.githubapisampleproject.presentation.user.UsersFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, UserViewModel>() {

    private val userViewModel: UserViewModel by viewModels()

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModel() = userViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        destination.directionUserFragment(supportFragmentManager, UsersFragment())

    }



}