package com.coldblue.explain

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.coldblue.designsystem.component.HMButton
import com.coldblue.designsystem.component.HMNavigateAnimation.fadeInScreen
import com.coldblue.designsystem.component.HMNavigateAnimation.fadeOutScreen
import com.coldblue.designsystem.component.HMPagerIndicator
import com.coldblue.designsystem.component.HMText
import com.coldblue.designsystem.component.calculateCurrentOffsetForPage
import com.coldblue.designsystem.theme.HMColor
import com.coldblue.designsystem.theme.HmStyle
import com.colddelight.explain.R
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExplainScreen(
    explainViewModel: ExplainViewModel = hiltViewModel(),
    onFinished: () -> Unit,
) {
    val pageState = rememberPagerState(pageCount = { 4 })
    val fadeAlpha = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        explainViewModel.updateExplainState()
        fadeAlpha.fadeInScreen()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .alpha(fadeAlpha.value)
    ) {
        HorizontalPager(
            state = pageState,
            beyondBoundsPageCount = 0,
        ) { page ->
            Surface(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = pageState.calculateCurrentOffsetForPage(page)
                        translationX = pageOffset * size.width
                        alpha = 1 - pageOffset.absoluteValue
                    }
                    .background(HMColor.Background)
                    .fillMaxSize()
                    .padding(top = 90.dp)
                    .padding(horizontal = 30.dp)
            ) {
                ExplainPage(page)
            }
        }

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.padding(top = 30.dp)
        ) {
            HMPagerIndicator(
                pageCount = pageState.pageCount,
                currentPage = pageState.currentPage,
                targetPage = pageState.targetPage,
                currentPageOffsetFraction = pageState.currentPageOffsetFraction,
            )
        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .padding(bottom = 30.dp)
                .padding(horizontal = 30.dp)
        ) {
            HMButton(
                text = if (pageState.currentPage == 3)
                    stringResource(id = R.string.explain_finish) else stringResource(id = R.string.explain_next),
                clickableState = true
            ) {
                coroutineScope.launch {
                    val current = pageState.currentPage
                    if (current == 3) {
                        fadeAlpha.fadeOutScreen()
                        onFinished()
                    }
                    else
                        pageState.animateScrollToPage(pageState.currentPage + 1)
                }
            }
        }
    }
}

@Composable
fun ExplainPage(
    pageCount: Int,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(HMColor.Background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,

        ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(40.dp),
            ) {
                when (pageCount) {
                    0 -> FirstPage()
                    1 -> SecondPage()
                    2 -> ThirdPage()
                    else -> FourthPage()
                }
            }
        }
    }
}



@Composable
fun FirstPage() {
    Column {
        Text(
            text = stringResource(id = R.string.explain_title),
            style = HmStyle.text46,
            color = HMColor.Primary
        )
        Text(
            text = stringResource(id = R.string.explain_manda_mean),
            style = HmStyle.text12,
            color = HMColor.SubDarkText
        )
    }

    HMText(
        topText = stringResource(id = R.string.explain_content_top),
        targetText = stringResource(id = R.string.explain_content_tint),
        bottomText = stringResource(id = R.string.explain_content_bottom),
        tintColor = HMColor.Primary,
        fontSize = 18.sp
    )
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        painter = painterResource(id = R.drawable.explain_manda_third),
        contentDescription = ""
    )
}

@Composable
fun SecondPage() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        painter = painterResource(id = R.drawable.explain_manda_first),
        contentDescription = ""
    )
    Text(
        modifier = Modifier.padding(start = 10.dp),
        text = stringResource(id = R.string.explain_usage_1),
        style = HmStyle.text18,
        color = HMColor.Primary
    )
}

@Composable
fun ThirdPage() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        painter = painterResource(id = R.drawable.explain_manda_second),
        contentDescription = ""
    )
    Column(
        modifier = Modifier.padding(start = 10.dp)
    ) {
        Text(
            text = stringResource(id = R.string.explain_usage_1),
            style = HmStyle.text18
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.explain_usage_2),
            style = HmStyle.text18,
            color = HMColor.Primary
        )
    }
}

@Composable
fun FourthPage() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        painter = painterResource(id = R.drawable.explain_manda_third),
        contentDescription = ""
    )
    Column(
        modifier = Modifier.padding(start = 10.dp),
    ) {
        Text(
            text = stringResource(id = R.string.explain_usage_1),
            style = HmStyle.text18
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.explain_usage_2),
            style = HmStyle.text18
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.explain_usage_3),
            style = HmStyle.text18,
            color = HMColor.Primary
        )
    }

    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = stringResource(id = R.string.explain_manda_advantage),
        style = HmStyle.text20,
        color = HMColor.Primary,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun ExplainPagePreview() {
    FirstPage()
}
