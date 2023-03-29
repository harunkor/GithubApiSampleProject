package com.harunkor.githubapisampleproject.extension

import android.view.View

fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener { block(it as T) }