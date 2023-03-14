package com.harunkor.githubapisampleproject.utils.bindingadapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.harunkor.githubapisampleproject.utils.extension.loadImageFromUrl

@BindingAdapter(value = ["srcUrl", "placeHolder"])
fun loadImageFromBind(view: ImageView, srcUrl: String?, placeHolder: Drawable) {
    if (srcUrl.isNullOrEmpty()) {
        view.setImageDrawable(placeHolder)
    } else {
        view.loadImageFromUrl(srcUrl, placeHolder)
    }
}