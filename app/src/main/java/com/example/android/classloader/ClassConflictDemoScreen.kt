package com.example.android.classloader


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dalvik.system.DexClassLoader
import timber.log.Timber
import java.io.File

@Composable
fun ClassConflictDemoScreen() {
    val context = LocalContext.current
    var resultMessage by remember { mutableStateOf("点击按钮开始演示") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Class 冲突演示", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            // 模拟使用两个不同的 DexClassLoader 加载同一个类（假设 dex 文件 version1.dex 与 version2.dex 分别包含不同版本的 com.example.dynamic.MyClass）
            val dexPath1 = getDexPath(context, "version1.dex")
            val dexPath2 = getDexPath(context, "version2.dex")
            try {
                val optimizedDir1 = context.getDir("dex_opt1", Context.MODE_PRIVATE)
                val optimizedDir2 = context.getDir("dex_opt2", Context.MODE_PRIVATE)
                val loader1 = DexClassLoader(dexPath1, optimizedDir1.absolutePath, null, context.classLoader)
                val loader2 = DexClassLoader(dexPath2, optimizedDir2.absolutePath, null, context.classLoader)

                val className = "com.example.dynamic.MyClass"
                val classFromLoader1 = loader1.loadClass(className)
                val classFromLoader2 = loader2.loadClass(className)

                // 通过 cast 尝试将 loader2 加载的实例转换为 loader1 加载的类型，预期将抛出 ClassCastException
                val instance1 = classFromLoader1.newInstance()
                val instance2 = classFromLoader2.newInstance()
                classFromLoader1.cast(instance2)
                resultMessage = "没有发生异常，演示失败"
            } catch (e: ClassCastException) {
                resultMessage = "捕获 ClassCastException，演示成功: ${e.message}"
            } catch (e: Exception) {
                resultMessage = "其他异常: ${e.message}"
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("开始演示")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = resultMessage)
    }
}

fun getDexPath(context: Context, dexFileName: String): String {
    // 假设 dex 文件存放在外部文件目录下（需要实际准备文件和适当的权限处理）
    val downloadsDir = context.getExternalFilesDir(null)
    return File(downloadsDir, dexFileName).absolutePath
}

@Preview
@Composable
fun ClassConflictDemoScreenPreview(){
    ClassConflictDemoScreen()
}
