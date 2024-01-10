package com.example.simple_todo.data.repository

import com.example.simple_todo.data.data_source.TodoDatabase
import com.example.simple_todo.domain.model.TodoEntity
import com.example.simple_todo.domain.repository.TodoRepo
import kotlinx.coroutines.flow.Flow

class TodoRepoImpl(
    private val database: TodoDatabase
): TodoRepo {
    private val dao = database.todoDao()

    override fun getTodos(): Flow<List<TodoEntity>> = dao.getTodos()
    override suspend fun addTodo(todoEntity: TodoEntity) = dao.addTodo(todoEntity)
    override suspend fun updateTodo(todoEntity: TodoEntity) = dao.updateTodo(todoEntity)
    override suspend fun deleteTodo(todoEntity: TodoEntity) = dao.deleteTodo(todoEntity)
}