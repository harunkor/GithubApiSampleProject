package com.harunkor.githubapisampleproject.domain.model.user

import com.google.gson.annotations.SerializedName

data class SearchUser(
    @SerializedName("total_count")
    val totalCount: Int? = null,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,
    @SerializedName("items")
    val items: List<User>? = null
)