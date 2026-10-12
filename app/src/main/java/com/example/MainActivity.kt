package com.example

import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.ui.AppScreen
import com.example.ui.MainViewModel
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.MonsterBattleScreen
import com.example.ui.screens.NinjaFightScreen
import com.example.ui.screens.NinjaRunScreen
import com.example.ui.screens.PythonAcademyScreen
import com.example.ui.screens.PythonMiniProjectsScreen
import com.example.ui.screens.RealPythonLabScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.TypingAcademyScreen
import com.example.ui.theme.TypingMasterTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Strict requirement: Never show on-screen keyboard automatically
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        setContent {
            TypingMasterTheme {
                val currentScreen by viewModel.currentScreen.collectAsState()

                // System BackHandler to safely return to previous screen or dashboard
                BackHandler(enabled = currentScreen != AppScreen.DASHBOARD) {
                    viewModel.navigateBack()
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.safeDrawing
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (currentScreen) {
                            AppScreen.DASHBOARD -> DashboardScreen(viewModel = viewModel)
                            AppScreen.NINJA_FIGHT -> NinjaFightScreen(viewModel = viewModel)
                            AppScreen.NINJA_RUN -> NinjaRunScreen(viewModel = viewModel)
                            AppScreen.MONSTER_BATTLE -> MonsterBattleScreen(viewModel = viewModel)
                            AppScreen.TYPING_ACADEMY -> TypingAcademyScreen(viewModel = viewModel)
                            AppScreen.PYTHON_ACADEMY -> PythonAcademyScreen(viewModel = viewModel)
                            AppScreen.REAL_PYTHON_LAB -> RealPythonLabScreen(viewModel = viewModel)
                            AppScreen.PYTHON_MINI_PROJECTS -> PythonMiniProjectsScreen(viewModel = viewModel)
                            AppScreen.SETTINGS -> SettingsScreen(viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }

    /**
     * Intercept hardware keyboard key events directly at Activity level.
     * Dispatches characters into the typing engine smoothly.
     */
    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            val unicode = event.unicodeChar
            if (unicode > 0) {
                val char = unicode.toChar()
                viewModel.onHardwareKeyChar(char)
                return true
            } else if (event.keyCode == KeyEvent.KEYCODE_DEL) {
                viewModel.onHardwareBackspace()
                return true
            } else if (event.keyCode == KeyEvent.KEYCODE_ESCAPE) {
                if (viewModel.navigateBack()) {
                    return true
                }
            }
        }
        return super.dispatchKeyEvent(event)
    }
}
