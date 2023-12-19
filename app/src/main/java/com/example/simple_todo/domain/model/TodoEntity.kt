package com.example.simple_todo.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.text.SimpleDateFormat

@Entity("todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("description")
    val description: String,

    @ColumnInfo("is_done")
    val isDone: Boolean = false,

    @ColumnInfo("timestamp")
    val timestamp: Long = System.currentTimeMillis()
)


val TodoEntity.addDate: String get() = SimpleDateFormat("yyyy/MM/dd hh:mm").format(Date(timestamp))
