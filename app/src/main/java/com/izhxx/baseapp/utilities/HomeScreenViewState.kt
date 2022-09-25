package com.izhxx.baseapp.utilities

import com.izhxx.baseapp.data.LocalBlogData
import com.izhxx.baseapp.ui.screens.home.views.BlogCardItemModel

sealed class HomeScreenViewState {
    object Loading : HomeScreenViewState()
    object Error : HomeScreenViewState()
    data class Display(
        val title: String,
        val items: List<BlogCardItemModel>
    ) : HomeScreenViewState()
    data class DisplayBlog(
        val item: LocalBlogData
    ) : HomeScreenViewState()
    object NoItems : HomeScreenViewState()
}