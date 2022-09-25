package com.izhxx.baseapp.data

data class LocalBlogData(
    val mainTitle: String = "Blog",
    val blogTitle: String,
    val blogSubtitle: String,
    val blogDescription: String,
    val blogImage: String,
    val blogContent: String,
    val blogCreateData: String,
    val blogLikeCount: Int,
    val contentTitle: String
)