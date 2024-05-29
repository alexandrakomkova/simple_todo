package com.example.simple_todo.data.repository

import com.example.simple_todo.domain.model.TodoEntity
import com.example.simple_todo.domain.repository.TodoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTodoRepo: TodoRepo {

		private val todos = mutableListOf<TodoEntity>()
		override fun getTodos(): Flow<List<TodoEntity>> {
				return flow { emit(todos) }
		}

		override suspend fun addTodo(todoEntity: TodoEntity) {
				todos.add(todoEntity)
		}

		override suspend fun updateTodo(todoEntity: TodoEntity) {
				todos.map { todo  ->
						if (todo.id == todoEntity.id) todoEntity else todo
				}
		}

		override suspend fun deleteTodo(todoEntity: TodoEntity) {
				todos.remove(todoEntity)
		}
}