package com.izhxx.baseapp.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import com.izhxx.baseapp.ui.HomeViewModel
import com.izhxx.baseapp.ui.screens.BlogScreen
import com.izhxx.baseapp.ui.screens.home.views.HomeViewDisplay
import com.izhxx.baseapp.ui.screens.home.views.HomeViewError
import com.izhxx.baseapp.ui.screens.home.views.HomeViewLoading
import com.izhxx.baseapp.ui.screens.home.views.HomeViewNoItems
import com.izhxx.baseapp.utilities.HomeScreenEvent
import com.izhxx.baseapp.utilities.HomeScreenViewState

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val viewState = homeViewModel.state.observeAsState()

    when (val state = viewState.value) {
        HomeScreenViewState.Loading -> HomeViewLoading()
        HomeScreenViewState.Error -> HomeViewError(
            onReloadClick = {
                homeViewModel.obtainEvent(HomeScreenEvent.ReloadScreen)
            }
        )
        is HomeScreenViewState.Display -> HomeViewDisplay(
            viewState = state,
            onCardClick = { id ->
                homeViewModel.obtainEvent(HomeScreenEvent.OnBlogClick(blogId = id))
            }
        )
        is HomeScreenViewState.DisplayBlog -> BlogScreen(
            viewState = state,
            onClick = {
                homeViewModel.obtainEvent(HomeScreenEvent.OnBackClick)
            }
        )
        HomeScreenViewState.NoItems -> HomeViewNoItems(onClick = {
            homeViewModel.obtainEvent(HomeScreenEvent.ReloadScreen)
        })
        else -> throw NotImplementedError("Unexpected state")
    }

    LaunchedEffect(key1 = viewState, block = {
        homeViewModel.obtainEvent(event = HomeScreenEvent.EnterScreen)
    })
}