package com.harunkor.githubapisampleproject.utils

import android.content.Context
import com.harunkor.githubapisampleproject.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringProvider @Inject constructor(@ApplicationContext private val  context: Context): StringProviderHelper {
    override fun getNetworkErrorMessage(): String = context.getString(R.string.network_error_message)
}