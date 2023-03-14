package com.harunkor.githubapisampleproject.utils

class ClickListener {

    interface ItemClickListener<DATA> {
        fun itemClicked(item: DATA)
    }
}