package com.izhxx.baseapp.data.repository

import com.izhxx.baseapp.data.api.BlogResponse
import com.izhxx.baseapp.data.api.MultipleBlogResponse
import com.izhxx.baseapp.data.api.RstApi
import com.izhxx.baseapp.data.api.RstApiResponse
import com.izhxx.baseapp.utilities.Result
import javax.inject.Inject

class RstRepository @Inject constructor(
    private val rstApi: RstApi
) {
    suspend fun getDataFromServer(id: String): Result<RstApiResponse> {
        return try {
            val response = rstApi.getMainPageData(id)

            if (response.isSuccessful) {
                Result.Successful(response.body()!!)
            } else {
                Result.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getMultipleBlogData(id: String): MultipleBlogResponse {
        return rstApi.getMultipleBlogData(id).body()!!
    }

    suspend fun getBlogData(id: String, blogId: String): BlogResponse {
        return rstApi.getBlogData(id, blogId).body()!!
    }
}