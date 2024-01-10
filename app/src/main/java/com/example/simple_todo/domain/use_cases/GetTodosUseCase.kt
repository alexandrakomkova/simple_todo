package com.example.simple_todo.domain.use_cases

import com.example.simple_todo.domain.model.TodoEntity
import com.example.simple_todo.domain.repository.TodoRepo
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

class GetTodosUseCase(
    private val repo: TodoRepo
): KoinComponent {
    operator fun invoke(): Flow<List<TodoEntity>> {
        return repo.getTodos()
    }
}