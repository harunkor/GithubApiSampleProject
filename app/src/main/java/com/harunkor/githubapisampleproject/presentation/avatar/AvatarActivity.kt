package com.harunkor.githubapisampleproject.presentation.avatar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.harunkor.githubapisampleproject.BR
import com.harunkor.githubapisampleproject.R
import com.harunkor.githubapisampleproject.databinding.ActivityAvatarBinding
import com.harunkor.githubapisampleproject.presentation.base.BaseActivity
import com.harunkor.githubapisampleproject.presentation.user.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvatarActivity : BaseActivity<ActivityAvatarBinding, UserViewModel>() {

    private val userViewModel: UserViewModel by viewModels()

    override fun getLayoutId() = R.layout.activity_avatar

    override fun getViewModel() = userViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setVariable(BR.avatarUrl,intent.getStringExtra(AVATAR_ITEM))
    }

    companion object {
        val AVATAR_ITEM = "avatar"
        fun callingIntent(ctx: Context, avatarUrl:String): Intent =
            Intent(ctx, AvatarActivity::class.java).apply {
                putExtra(AVATAR_ITEM, avatarUrl)
            }
    }
}