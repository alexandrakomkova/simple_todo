package com.example.simple_todo.domain.use_cases

data class TodoUseCases (
    val getTodosUseCase: GetTodosUseCase,
    val addTodoUseCase: AddTodoUseCase,
    val updateTodoUseCase: UpdateTodoUseCase,
    val deleteTodoUseCase: DeleteTodoUseCase
    )