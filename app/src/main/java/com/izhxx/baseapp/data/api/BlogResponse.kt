package com.izhxx.baseapp.data.api

import com.google.gson.annotations.SerializedName

data class BlogResponse(
    @field:SerializedName("success") val success: Boolean,
    @field:SerializedName("data") val data: BlogData
)

data class BlogData(
    @field:SerializedName("id") val blogId: Int,
    @field:SerializedName("url") val blogUrl: String,
    @field:SerializedName("image") val blogImages: BlogImage,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("subtitle") val subtitle: String,
    @field:SerializedName("like") val likeCount: Int,
    @field:SerializedName("date") val date: String,
    @field:SerializedName("content") val content: String
)

data class BlogImage(
    @field:SerializedName("sm") val smallImage: String,
    @field:SerializedName("md") val middleImage: String,
    @field:SerializedName("lg") val largeImage: String,
)
