package com.izhxx.baseapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.izhxx.baseapp.R
import com.izhxx.baseapp.ui.theme.Typography
import com.izhxx.baseapp.utilities.HomeScreenViewState
import com.izhxx.baseapp.utilities.PicassoInitializer
import com.squareup.picasso3.Picasso
import com.squareup.picasso3.compose.rememberPainter

@Composable
fun BlogScreen(
    viewState: HomeScreenViewState.DisplayBlog,
    onClick: () -> Unit,
    picasso: Picasso = PicassoInitializer.get()
) {
    val columnState = rememberLazyListState()
    val buttonVisibility by derivedStateOf {
        columnState.firstVisibleItemIndex == 0
    }
    LazyColumn(
        state = columnState,
        modifier = Modifier.padding(bottom = 60.dp)
    ) {
        item {
            Image(
                painter = picasso.rememberPainter {
                    it.load(viewState.item.blogImage)
                        .placeholder(R.drawable.image_load_error)
                        .error(R.drawable.image_load_error)
                },
                contentDescription = "Blog Image"
            )
        }
        item {
            Text(
                text = viewState.item.blogCreateData,
                Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
                style = Typography.subtitle1
            )
        }
        item {
            Text(
                text = viewState.item.blogSubtitle,
                Modifier.padding(start = 16.dp, end = 16.dp),
                style = Typography.h1
            )
        }
        item {
            Text(
                text = viewState.item.blogContent,
                Modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp),
                style = Typography.body1
            )
        }
    }

    backButton(
        onClick = onClick,
        modifier = Modifier.padding(20.dp, 20.dp, 0.dp, 0.dp).size(40.dp),
        isVisibleBecauseOfScrolling = buttonVisibility
    )
}

@Composable
fun backButton(
    onClick: () -> Unit,
    modifier: Modifier,
    isVisibleBecauseOfScrolling: Boolean
) {
    AnimatedVisibility(
        visible = isVisibleBecauseOfScrolling,
        enter = fadeIn(animationSpec = keyframes { this.durationMillis = 80 }),
        exit = fadeOut(animationSpec = keyframes { this.durationMillis = 80 })
    ) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            shape = CircleShape,
            border = BorderStroke(2.dp, Color.White),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Button",
                tint = Color.Black
            )
        }
    }
}