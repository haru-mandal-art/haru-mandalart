package com.coldblue.tutorial

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.coldblue.designsystem.component.HMButton
import com.coldblue.designsystem.component.HMNavigateAnimation.fadeOutScreen
import com.coldblue.designsystem.theme.HMColor
import com.coldblue.designsystem.theme.HmStyle
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorialScreen(
    offset: Offset = Offset.Zero,
    size: IntSize = IntSize.Zero,
    onFinished: () -> Unit
) {

    val explainList = HistoryUtil.getExplainList()
    val pagerState = rememberPagerState { 4 }
    val coroutineScope = rememberCoroutineScope()
    val fadeAlpha = remember { Animatable(0.5f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(fadeAlpha.value)
            .background(Color.Black)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(false) {}
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset {
                    IntOffset(
                        offset.x.toInt(),
                        offset.y.toInt() + size.height
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = explainList[pagerState.currentPage],
                style = HmStyle.text16,
                color = HMColor.Background
            )

        }

        HMButton(
            text = if (pagerState.currentPage == 3) stringResource(id = R.string.tutorial_finish) else stringResource(
                id = R.string.tutorial_next
            ),
            clickableState = true
        ) {
            coroutineScope.launch {
                if (pagerState.currentPage == 3) {
                    onFinished()
                    fadeAlpha.fadeOutScreen()
                } else {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        }
    }
}