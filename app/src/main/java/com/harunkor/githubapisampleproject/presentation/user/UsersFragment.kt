package com.harunkor.githubapisampleproject.presentation.user

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.harunkor.githubapisampleproject.R
import com.harunkor.githubapisampleproject.databinding.FragmentUsersBinding
import com.harunkor.githubapisampleproject.domain.model.user.User
import com.harunkor.githubapisampleproject.presentation.base.BaseFragment
import com.harunkor.githubapisampleproject.presentation.base.BaseRecyclerAdapter
import com.harunkor.githubapisampleproject.extension.observe
import com.harunkor.githubapisampleproject.utils.ClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment: BaseFragment<FragmentUsersBinding,UserViewModel>(),
    ClickListener.ItemClickListener<User> {

    private val userViewModel: UserViewModel by activityViewModels()

    private val usersAdapter =  BaseRecyclerAdapter<ClickListener.ItemClickListener<User>,User>(
            R.layout.item_user,this@UsersFragment
    )

    override fun getLayoutId() = R.layout.fragment_users

    override fun getViewModel(): UserViewModel = userViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initData()
        initObservers()
    }

    private fun initData() = with(userViewModel){
        getUserList()
    }

    private fun initObservers() = with(userViewModel) {
        observe(userList){
            usersAdapter.apply {
                setData(it)
            }



        }
    }

    private fun initListeners() = with(binding) {
            recylerUsers.adapter = usersAdapter
    }

    override fun itemClicked(item: User) {
        item.login?.let { destination.navigateUserListToDetail(findNavController(), it) }
    }


}