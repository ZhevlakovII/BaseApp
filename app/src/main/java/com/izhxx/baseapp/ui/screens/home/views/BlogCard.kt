package com.izhxx.baseapp.ui.screens.home.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.izhxx.baseapp.R
import com.izhxx.baseapp.ui.theme.Shapes
import com.izhxx.baseapp.ui.theme.Typography
import com.izhxx.baseapp.utilities.PicassoInitializer
import com.squareup.picasso3.Picasso
import com.squareup.picasso3.compose.rememberPainter

data class BlogCardItemModel(
    val blogId: Int,
    val title: String,
    val description: String,
    val imageUrl: String
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BlogCard(
    model: BlogCardItemModel,
    onCardClick: (Int) -> Unit,
    picasso: Picasso = PicassoInitializer.get()
) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp,
        shape = Shapes.medium,
        onClick = { onCardClick(model.blogId) }
    ) {
        Column {
            CustomText(text = model.title, typography = Typography.h1,
                modifier = Modifier.padding(8.dp))
            Image(
                painter = picasso.rememberPainter {
                    it.load(model.imageUrl)
                        .placeholder(R.drawable.image_load_error)
                        .error(R.drawable.image_load_error)
                },
                contentDescription = "Blog Image"
            )
            CustomText(text = model.description, typography = Typography.body2,
                modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun CustomText(
    text: String,
    typography: TextStyle,
    modifier: Modifier
) {
    Text(
        text = text,
        style = typography,
        modifier = modifier,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}