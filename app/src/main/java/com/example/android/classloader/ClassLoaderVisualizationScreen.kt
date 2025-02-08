package com.example.android.classloader


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Composable
fun ClassLoaderVisualizationScreen() {
    var className by remember { mutableStateOf("java.lang.String") }
    var loaderChain by remember { mutableStateOf(listOf<String>()) }
    var errorMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "ClassLoader 可视化", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = className,
            onValueChange = { className = it },
            label = { Text("请输入类名") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            try {
                val clazz = Class.forName(className)
                loaderChain = getClassLoaderChain(clazz)
                errorMessage = ""
            } catch (e: Exception) {
                Timber.e(e)
                errorMessage = "错误: ${e.message}"
                loaderChain = listOf()
            }
        }) {
            Text("获取 ClassLoader 链")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colors.error)
        }
        LazyColumn {
            items(loaderChain) { loader ->
                Text(text = loader)
                Divider()
            }
        }
    }
}

fun getClassLoaderChain(clazz: Class<*>): List<String> {
    val loaders = mutableListOf<String>()
    var currentLoader: ClassLoader? = clazz.classLoader
    while (currentLoader != null) {
        loaders.add(currentLoader.toString())
        currentLoader = currentLoader.parent
    }
    if (loaders.isEmpty()) {
        loaders.add("Bootstrap ClassLoader")
    }
    return loaders
}

@Preview
@Composable
fun ClassLoaderVisualizationScreenPreview(){
    ClassLoaderVisualizationScreen()
}

