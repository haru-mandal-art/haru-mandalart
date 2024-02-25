package com.coldblue.haru_mandalart

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coldblue.data.util.LoginHelper
import com.coldblue.data.util.LoginState
import com.coldblue.haru_mandalart.ui.HMApp
import com.coldblue.designsystem.theme.HarumandalartTheme
import com.coldblue.login.LoginScreen
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var loginHelper: LoginHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HarumandalartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BackOnPressed()
                    HMApp()
//                    loginHelper.isLogin.collectAsStateWithLifecycle(LoginState.Loading).value.let {
//                        when(it){
//                            LoginState.Logout -> LoginScreen()
//                            LoginState.Login -> HMApp()
//                            else -> {}
//                        }
//                    }
                }
            }
        }
    }

    @Composable
    fun BackOnPressed() {
        val context = LocalContext.current
        var backPressedState by remember { mutableStateOf(true) }
        var backPressedTime = 0L

        BackHandler(enabled = backPressedState) {
            if (System.currentTimeMillis() - backPressedTime <= 1000L) {

                (context as Activity).finish()
            } else {
                backPressedState = true
                Toast.makeText(context, "한 번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
                backPressedTime = System.currentTimeMillis()
            }
        }
    }
}