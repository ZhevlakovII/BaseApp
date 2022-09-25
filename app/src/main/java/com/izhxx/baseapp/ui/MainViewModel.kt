package com.izhxx.baseapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.izhxx.baseapp.BuildConfig
import com.izhxx.baseapp.data.LocalBlogData
import com.izhxx.baseapp.data.repository.RstRepository
import com.izhxx.baseapp.ui.screens.home.views.BlogCardItemModel
import com.izhxx.baseapp.utilities.EventHandler
import com.izhxx.baseapp.utilities.HomeScreenEvent
import com.izhxx.baseapp.utilities.HomeScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.izhxx.baseapp.utilities.Result

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val rstRepository: RstRepository
) : ViewModel(), EventHandler<HomeScreenEvent> {
    private val _state: MutableLiveData<HomeScreenViewState> =
        MutableLiveData(HomeScreenViewState.Loading)
    val state: LiveData<HomeScreenViewState> = _state

    private val _contentArray = arrayListOf<LocalBlogData>()

    private val currentBlogData: MutableLiveData<LocalBlogData> = MutableLiveData()

    override fun obtainEvent(event: HomeScreenEvent) {
        when (val currentState = _state.value) {
            is HomeScreenViewState.Loading -> reduce(event, currentState)
            is HomeScreenViewState.Error -> reduce(event, currentState)
            is HomeScreenViewState.Display -> reduce(event, currentState)
            is HomeScreenViewState.DisplayBlog -> reduce(event, currentState)
            is HomeScreenViewState.NoItems -> reduce(event, currentState)
            else -> throw NotImplementedError("Unexpected event $event")
        }
    }

    private fun reduce(event: HomeScreenEvent, currentState: HomeScreenViewState.DisplayBlog) {
        when (event) {
            HomeScreenEvent.EnterScreen -> onBlogReload()
            HomeScreenEvent.ReloadScreen -> onBlogReload()
            HomeScreenEvent.OnBackClick -> onBackClick()
            else -> throw NotImplementedError("Unexpected event $event")
        }
    }

    private fun reduce(event: HomeScreenEvent, currentState: HomeScreenViewState.Loading) {
        when (event) {
            HomeScreenEvent.EnterScreen -> getDataFromServer()
            else -> throw NotImplementedError("Unexpected event $event")
        }
    }

    private fun reduce(event: HomeScreenEvent, currentState: HomeScreenViewState.Error) {
        when (event) {
            HomeScreenEvent.EnterScreen -> getDataFromServer()
            HomeScreenEvent.ReloadScreen -> getDataFromServer()
            else -> throw NotImplementedError("Unexpected event $event")
        }
    }

    private fun reduce(event: HomeScreenEvent, currentState: HomeScreenViewState.Display) {
        when (event) {
            HomeScreenEvent.EnterScreen -> getDataFromServer()
            HomeScreenEvent.ReloadScreen -> getDataFromServer()
            is HomeScreenEvent.OnBlogClick -> setBlogData(event.blogId)
            else -> throw NotImplementedError("Unexpected event $event")
        }
    }

    private fun reduce(event: HomeScreenEvent, currentState: HomeScreenViewState.NoItems) {
        when (event) {
            HomeScreenEvent.ReloadScreen -> getDataFromServer()
            else -> throw NotImplementedError("Unexpected event $event")
        }
    }

    private fun onBackClick() {
        viewModelScope.launch {
            _state.postValue(HomeScreenViewState.Loading)

            var id = 0
            val blogCards: List<BlogCardItemModel> = _contentArray.map { blogData ->
                BlogCardItemModel(
                    blogId = id++,
                    title = blogData.blogTitle,
                    description = blogData.blogDescription,
                    imageUrl = blogData.blogImage
                )
            }
            _state.postValue(
                HomeScreenViewState.Display(
                    title = _contentArray[0].contentTitle,
                    items = blogCards
                )
            )
        }
    }

    private fun setBlogData(blogId: Int) {
        viewModelScope.launch {
            _state.postValue(HomeScreenViewState.DisplayBlog(
                item = _contentArray[blogId]
            ))

            currentBlogData.postValue(_contentArray[blogId])
        }
    }

    private fun onBlogReload() {
        viewModelScope.launch {
            currentBlogData.value.let { data ->
                if (data != null) {
                    _state.postValue(
                        HomeScreenViewState.DisplayBlog(
                            item = data
                        )
                    )
                } else {
                    _state.postValue(HomeScreenViewState.Error)
                }
            }
        }
    }

    private fun getDataFromServer() {
        viewModelScope.launch {
            _state.postValue(HomeScreenViewState.Loading)
            _contentArray.clear()

            when (val homePageData = rstRepository.getDataFromServer(BuildConfig.BASE_ID)) {
                is Result.Successful -> {
                    homePageData.data.responseData.content.forEach { content ->
                        if (content.contentTemplate.contentObject == "blog") {
                            val multipleBlogData =
                                rstRepository.getMultipleBlogData(BuildConfig.BASE_ID)

                            if (multipleBlogData.success) {
                                multipleBlogData.data.forEach { blogData ->
                                    val blogContent = rstRepository.getBlogData(
                                        BuildConfig.BASE_ID,
                                        blogData.blogId.toString()
                                    )

                                    _contentArray.add(
                                        LocalBlogData(
                                            blogTitle = blogData.title,
                                            blogSubtitle = blogContent.data.subtitle,
                                            blogDescription = blogData.description,
                                            blogImage = blogContent.data.blogImages.largeImage,
                                            blogContent = blogContent.data.content,
                                            blogCreateData = blogContent.data.date,
                                            blogLikeCount = blogContent.data.likeCount,
                                            contentTitle = content.contentName
                                        )
                                    )
                                }
                            } else {
                                _state.postValue(HomeScreenViewState.Error)
                            }

                            var id = 0
                            val blogCards: List<BlogCardItemModel> = _contentArray.map { blogData ->
                                BlogCardItemModel(
                                    blogId = id++,
                                    title = blogData.blogTitle,
                                    description = blogData.blogDescription,
                                    imageUrl = blogData.blogImage
                                )
                            }
                            _state.postValue(
                                HomeScreenViewState.Display(
                                    title = content.contentName,
                                    items = blogCards
                                )
                            )
                        }
                    }
                }
                is Result.Error -> {
                    _state.postValue(HomeScreenViewState.Error)
                }
            }
        }
    }
}