package com.example.simple_todo.domain.repository

import com.example.simple_todo.domain.model.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepo {
    suspend fun getTodos(): Flow<List<TodoEntity>>
    suspend fun addTodo(todoEntity: TodoEntity)
    suspend fun updateTodo(todoEntity: TodoEntity)
    suspend fun deleteTodo(todoEntity: TodoEntity)
}