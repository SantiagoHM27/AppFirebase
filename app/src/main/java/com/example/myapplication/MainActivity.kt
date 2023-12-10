package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.FunFactsNavigationGraph
import com.example.myapplication.ui.screens.Routes
import com.example.myapplication.ui.screens.UserInputScreen
import com.example.myapplication.ui.screens.WelcomeScreen
import com.example.myapplication.ui.theme.FunFactsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunFactsTheme {
                FunFactApp()
            }
        }
    }
}

@Composable
fun FunFactApp(){
    FunFactsNavigationGraph()
}