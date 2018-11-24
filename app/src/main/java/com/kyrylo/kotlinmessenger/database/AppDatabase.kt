package com.kyrylo.kotlinmessenger.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kyrylo.kotlinmessenger.data.preferences.model.GoogleNews
import com.kyrylo.kotlinmessenger.database.dao.TaskDAO
import com.kyrylo.kotlinmessenger.database.entities.Task

@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO
}