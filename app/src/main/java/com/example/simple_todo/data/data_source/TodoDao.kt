package com.example.simple_todo.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.simple_todo.domain.model.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert
    fun addTodo(todoEntity: TodoEntity)

    @Query("select * from `todos`")
    fun getTodos(): Flow<List<TodoEntity>>

    @Update
    fun updateTodo(todoEntity: TodoEntity)

    @Delete
    fun deleteTodo(todoEntity: TodoEntity)
}