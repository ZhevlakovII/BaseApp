package com.izhxx.baseapp.data.api

import com.google.gson.annotations.SerializedName

data class MultipleBlogResponse(
    @field:SerializedName("success") val success: Boolean,
    @field:SerializedName("data") val data: List<MultipleBlogData>
)

data class MultipleBlogData(
    @field:SerializedName("id") val blogId: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("date") val createDate: String
)