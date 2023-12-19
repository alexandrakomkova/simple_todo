package com.example.simple_todo.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simple_todo.domain.model.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}