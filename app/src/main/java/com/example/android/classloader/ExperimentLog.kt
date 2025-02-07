package com.example.android.classloader


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "experiment_logs")
data class ExperimentLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val experimentType: String,
    val result: String,
    val timestamp: Long = System.currentTimeMillis()
)
