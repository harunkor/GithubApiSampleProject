package com.harunkor.githubapisampleproject

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.harunkor.githubapisampleproject.databinding.ActivityMainBinding
import com.harunkor.githubapisampleproject.presentation.base.BaseActivity
import com.harunkor.githubapisampleproject.presentation.user.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, UserViewModel>() {

    private val userViewModel: UserViewModel by viewModels()

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModel() = userViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            val navHostFragment  = supportFragmentManager
                .findFragmentById(fragmentcontainerview.id) as NavHostFragment
            NavigationUI.setupWithNavController(bottomNav,navHostFragment.navController)
        }
    }


}