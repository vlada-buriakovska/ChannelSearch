package com.vlada.channels.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.compose.material3.SnackbarResult.Dismissed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vlada.channels.R


@Composable
fun ErrorSnackBar(
    error: String,
    onDismissed: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = stringResource(R.string.all_ok),
) {
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(snackBarHostState) {
        val action = snackBarHostState.showSnackbar(
            message = error,
            actionLabel = label,
            duration = Indefinite,
        )
        when (action) {
            Dismissed -> {
                onDismissed.invoke()
            }

            ActionPerformed -> {
                onDismissed.invoke()
            }
        }
    }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        SnackbarHost(
            hostState = snackBarHostState,
        ) {
            Snackbar(
                snackbarData = it
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConnectionErrorPreview() {
    ErrorSnackBar(
        error = "Error",
        onDismissed = {}
    )
}