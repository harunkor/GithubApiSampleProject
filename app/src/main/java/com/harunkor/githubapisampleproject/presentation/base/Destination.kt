package com.harunkor.githubapisampleproject.presentation.base

import androidx.navigation.NavController
import com.harunkor.githubapisampleproject.presentation.user.UsersFragmentDirections
import javax.inject.Singleton

@Singleton
class Destination {

    fun navigateUserListToDetail(navController: NavController,userName: String){
          navController.navigate(UsersFragmentDirections.actionUsersFragmentToDetailFragment(userName))
    }

}