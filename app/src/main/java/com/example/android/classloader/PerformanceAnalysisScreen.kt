package com.example.android.classloader


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.system.measureTimeMillis

@Composable
fun PerformanceAnalysisScreen() {
    val context = LocalContext.current
    var dexPath by remember { mutableStateOf("") }
    var className by remember { mutableStateOf("") }
    var loadTime by remember { mutableStateOf(0L) }
    var resultMessage by remember { mutableStateOf("请输入 dex 文件路径和类名") }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "性能分析工具", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = dexPath,
            onValueChange = { dexPath = it },
            label = { Text("Dex 文件路径") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = className,
            onValueChange = { className = it },
            label = { Text("类名 (全限定名)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            scope.launch {
                val timeTaken = measureTimeMillis {
                    val clazz = loadClassFromDex(context, dexPath, className)
                    resultMessage = if (clazz != null) "加载成功: ${clazz.name}" else "加载失败"
                }
                loadTime = timeTaken
                Toast.makeText(context, "加载时间: $loadTime ms", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("分析加载时间")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "加载时间: $loadTime ms")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = resultMessage)
    }
}
