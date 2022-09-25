package com.izhxx.baseapp.data.api

import com.google.gson.annotations.SerializedName

data class RstApiResponse(
    @field:SerializedName("success") val success: Boolean,
    @field:SerializedName("data") val responseData: ResponseData
)

data class ResponseData(
    @field:SerializedName("buttons") val buttons: List<ResponseButtons>,
    @field:SerializedName("content") val content: List<ResponseContent>
)

data class ResponseButtons(
    @field:SerializedName("icon") val iconName: String,
    @field:SerializedName("color") val buttonColor: String,
    @field:SerializedName("title") val buttonText: String,
    @field:SerializedName("url") val redirectUrl: String
)

data class ResponseContent(
    @field:SerializedName("title") val contentName: String,
    @field:SerializedName("template") val contentTemplate: ContentTemplate,
    @field:SerializedName("url") val contentUrl: String
)

data class ContentTemplate(
    @field:SerializedName("object") val contentObject: String
)
