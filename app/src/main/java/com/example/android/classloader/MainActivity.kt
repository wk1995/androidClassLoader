package com.example.android.classloader


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 Timber 日志
        Timber.plant(Timber.DebugTree())

        setContent {
            ClassLoaderExplorerTheme {
                AppNavHost()
            }
        }
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("visualization") { ClassLoaderVisualizationScreen() }
        composable("dynamic_loading") { DynamicLoadingScreen() }
        composable("class_conflict") { ClassConflictDemoScreen() }
        composable("performance") { PerformanceAnalysisScreen() }
        composable("logs") { ExperimentLogScreen() }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ClassLoaderExplorerTheme {
        HomeScreen(navController = rememberNavController())
    }
}
