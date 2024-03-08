package com.coldblue.setting.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coldblue.designsystem.component.HMButton
import com.coldblue.designsystem.component.HMSwitch
import com.coldblue.designsystem.theme.HMColor
import com.coldblue.designsystem.theme.HmStyle

@Composable
fun SettingContent(
    showOss: () -> Unit,
    showPlayStore: () -> Unit


) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HMColor.Background)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            SettingItem(title = "알림") {
                HMSwitch(checked = true) {

                }
            }
            SettingItem(title = "현재계정") {
                Text(text = "hno05039@gmail.com")
            }
            SettingItem(title = "문의하기", isClickable = true, onClick = {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "문의"
                )
            }
            SettingItem(title = "앱 평가하기", isClickable = true, onClick = { showPlayStore() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "앱 평가"
                )
            }
            SettingItem(title = "오픈소스 라이센스", isClickable = true, onClick = { showOss() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "오픈소스 라이센스"
                )
            }
            SettingItem(title = "앱 버전") {
                Text(text = "v 1.0")
            }
            SettingItem(title = "탈퇴", isLast = true, isClickable = true, onClick = {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "탈퇴"
                )
            }
        }
        HMButton(text = "로그아웃", clickableState = true) {
        }
    }
}

@Composable
fun SettingItem(
    title: String,
    isLast: Boolean = false,
    isClickable: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(isClickable) {
                if (onClick != null) {
                    onClick()
                }
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = HmStyle.text16)
        content()
    }
    if (!isLast)
        HorizontalDivider(
            color = HMColor.SubText,
        )
}

@Preview
@Composable
fun SettingContentPreview() {
    SettingContent({},{})

}
