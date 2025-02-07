package com.example.android.classloader


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "ClassLoader Explorer", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("visualization") }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "ClassLoader 可视化")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("dynamic_loading") }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "动态加载与热替换实验")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("class_conflict") }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Class 冲突演示")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("performance") }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "性能分析工具")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("logs") }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "实验日志记录")
        }
    }
}
