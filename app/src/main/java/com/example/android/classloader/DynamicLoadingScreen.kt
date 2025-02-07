package com.example.android.classloader


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dalvik.system.DexClassLoader
import timber.log.Timber
import java.io.File

@Composable
fun DynamicLoadingScreen() {
    val context = LocalContext.current
    var dexPath by remember { mutableStateOf("") }
    var className by remember { mutableStateOf("") }
    var loadResult by remember { mutableStateOf("未加载") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "动态加载与热替换实验", style = MaterialTheme.typography.h5)
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
            val clazz = loadClassFromDex(context, dexPath, className)
            loadResult = if (clazz != null) "加载成功: ${clazz.name}" else "加载失败"
            Toast.makeText(context, loadResult, Toast.LENGTH_SHORT).show()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("加载类")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "结果: $loadResult")
    }
}

fun loadClassFromDex(context: Context, dexPath: String, className: String): Class<*>? {
    try {
        val file = File(dexPath)
        if (!file.exists()) {
            Timber.e("Dex file not found at $dexPath")
            return null
        }
        val optimizedDir = context.getDir("dex_opt", Context.MODE_PRIVATE)
        val loader = DexClassLoader(dexPath, optimizedDir.absolutePath, null, context.classLoader)
        return loader.loadClass(className)
    } catch (e: Exception) {
        Timber.e(e, "Error loading class from dex")
        return null
    }
}
