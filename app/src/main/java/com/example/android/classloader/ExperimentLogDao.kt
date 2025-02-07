package com.example.android.classloader


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExperimentLogDao {

    @Query("SELECT * FROM experiment_logs ORDER BY timestamp DESC")
     fun getAllLogs(): List<ExperimentLog>

    @Insert
     fun insertLog(log: ExperimentLog)
}
