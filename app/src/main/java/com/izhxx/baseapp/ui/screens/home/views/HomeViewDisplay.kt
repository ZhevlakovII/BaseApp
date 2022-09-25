package com.izhxx.baseapp.ui.screens.home.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.izhxx.baseapp.ui.theme.Typography
import com.izhxx.baseapp.utilities.HomeScreenViewState

@Composable
fun HomeViewDisplay(
    viewState: HomeScreenViewState.Display,
    onCardClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), content = {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = viewState.title,
                    modifier = Modifier.padding(12.dp, 12.dp, 0.dp, 4.dp),
                    style = Typography.h1
                )
            }
            viewState.items.forEach { card ->
                item {
                    BlogCard(model = card, onCardClick = onCardClick)
                }
            }
        },
        modifier = Modifier
            .padding(4.dp, bottom = 60.dp)
    )
}