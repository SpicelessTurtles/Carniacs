package com.kyrylo.kotlinmessenger.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "article")
data class Task(@ColumnInfo(name = "background_url") var backgroundUrl: String,
                @ColumnInfo(name = "title") var title: String,
                @ColumnInfo(name = "description") var desription: String,
                @ColumnInfo(name = "content") var content: String) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}