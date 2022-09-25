package com.izhxx.baseapp.utilities

sealed class HomeScreenEvent {
    object EnterScreen : HomeScreenEvent()
    object ReloadScreen : HomeScreenEvent()
    object OnBackClick : HomeScreenEvent()
    data class OnBlogClick(val blogId: Int) : HomeScreenEvent()
}