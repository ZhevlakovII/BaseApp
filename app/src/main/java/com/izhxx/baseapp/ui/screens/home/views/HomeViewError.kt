package com.izhxx.baseapp.ui.screens.home.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.izhxx.baseapp.R

@Composable
fun HomeViewError(
    onReloadClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = androidx.compose.ui.R.string.default_error_message),
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.Center)
        )
        Button(
            onClick = onReloadClick,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = stringResource(id = R.string.retry_button_text))
        }
    }
}