package com.harunkor.githubapisampleproject.presentation.base

import android.content.Context
import androidx.navigation.NavController
import com.harunkor.githubapisampleproject.presentation.avatar.AvatarActivity
import com.harunkor.githubapisampleproject.presentation.user.UsersFragmentDirections
import javax.inject.Singleton

@Singleton
class Destination {

    fun navigateUserListToDetail(navController: NavController,userName: String){
          navController.navigate(UsersFragmentDirections.actionUsersFragmentToDetailFragment(userName))
    }

    fun navigateToAvatar(context: Context,avatarUrl: String) {
        context.startActivity(
            AvatarActivity.callingIntent(context,avatarUrl)
        )
    }

}