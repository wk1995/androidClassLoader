package com.example.android.classloader


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ExperimentLogScreen() {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val logDao = db.experimentLogDao()
    val scope = rememberCoroutineScope()
    var logs by remember { mutableStateOf(listOf<ExperimentLog>()) }
    var experimentType by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "实验日志记录", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = experimentType,
            onValueChange = { experimentType = it },
            label = { Text("实验类型") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = result,
            onValueChange = { result = it },
            label = { Text("实验结果") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            scope.launch {
                logDao.insertLog(ExperimentLog(experimentType = experimentType, result = result))
                logs = logDao.getAllLogs()
                Toast.makeText(context, "日志已保存", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("保存日志")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "日志列表：", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(logs) { log ->
                Text(text = "${log.experimentType}: ${log.result} (${log.timestamp})")
                Divider()
            }
        }
    }

    // 首次加载日志
    LaunchedEffect(Unit) {
        logs = logDao.getAllLogs()
    }
}
