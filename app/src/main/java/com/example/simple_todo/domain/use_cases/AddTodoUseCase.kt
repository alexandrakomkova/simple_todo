package com.example.simple_todo.domain.use_cases

import com.example.simple_todo.domain.model.TodoEntity
import com.example.simple_todo.domain.repository.TodoRepo
import org.koin.core.component.KoinComponent

class AddTodoUseCase(
    private val repo: TodoRepo
    ) : KoinComponent {

    suspend operator fun invoke(todo: TodoEntity) {
        repo.addTodo(todo)
    }
}