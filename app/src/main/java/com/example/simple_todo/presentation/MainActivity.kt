package com.example.simple_todo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.simple_todo.presentation.home.HomeScreen
import com.example.simple_todo.presentation.home.HomeViewModel
import com.example.simple_todo.ui.theme.Simple_todoTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Simple_todoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: HomeViewModel = koinViewModel()
                    val state = viewModel.state
                    val context = LocalContext.current

                    HomeScreen(
                        viewModel = viewModel,
                        state = state,
                        onEvent = viewModel::onEvent,
                        context = context
                    )
                }
            }
        }
    }
}