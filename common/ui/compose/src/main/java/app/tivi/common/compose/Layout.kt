// Copyright 2021, Google LLC, Christopher Banes and the Tivi project contributors
// SPDX-License-Identifier: Apache-2.0

package app.tivi.common.compose

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isFinite

object Layout {

    val bodyMargin: Dp
        @Composable get() = when (LocalConfiguration.current.screenWidthDp) {
            in 0..599 -> 16.dp
            in 600..904 -> 32.dp
            in 905..1239 -> 0.dp
            in 1240..1439 -> 200.dp
            else -> 0.dp
        }

    val gutter: Dp
        @Composable get() = when (LocalConfiguration.current.screenWidthDp) {
            in 0..599 -> 8.dp
            in 600..904 -> 16.dp
            in 905..1239 -> 16.dp
            in 1240..1439 -> 32.dp
            else -> 32.dp
        }

    val bodyMaxWidth: Dp
        @Composable get() = when (LocalConfiguration.current.screenWidthDp) {
            in 0..599 -> Dp.Infinity
            in 600..904 -> Dp.Infinity
            in 905..1239 -> 840.dp
            in 1240..1439 -> Dp.Infinity
            else -> 1040.dp
        }

    val columns: Int
        @Composable get() = when (LocalConfiguration.current.screenWidthDp) {
            in 0..599 -> 4
            in 600..904 -> 8
            else -> 12
        }
}

fun Modifier.bodyWidth() = fillMaxWidth()
    .wrapContentWidth(align = Alignment.CenterHorizontally)
    .composed {
        val bodyMaxWidth = Layout.bodyMaxWidth
        if (bodyMaxWidth.isFinite) widthIn(max = bodyMaxWidth) else this
    }
    .composed {
        padding(
            WindowInsets.systemBars
                .only(WindowInsetsSides.Horizontal)
                .asPaddingValues(),
        )
    }
