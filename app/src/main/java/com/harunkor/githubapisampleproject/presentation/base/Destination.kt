package com.harunkor.githubapisampleproject.presentation.base

import androidx.fragment.app.FragmentManager
import com.harunkor.githubapisampleproject.presentation.user.UsersFragment
import javax.inject.Singleton

@Singleton
class Destination {

    fun directionUserFragment(fm: FragmentManager, fragment: UsersFragment) {
        fm.beginTransaction().add(android.R.id.content, fragment).commit()
    }

}