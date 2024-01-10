package com.example.simple_todo.di

import com.example.simple_todo.domain.use_cases.AddTodoUseCase
import com.example.simple_todo.domain.use_cases.DeleteTodoUseCase
import com.example.simple_todo.domain.use_cases.GetTodosUseCase
import com.example.simple_todo.domain.use_cases.TodoUseCases
import com.example.simple_todo.domain.use_cases.UpdateTodoUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<TodoUseCases> {
        TodoUseCases(
            getTodosUseCase = get(),
            addTodoUseCase = get(),
            deleteTodoUseCase = get(),
            updateTodoUseCase = get()
        )
    }

    factory<GetTodosUseCase> {
        GetTodosUseCase(repo = get())
    }

    factory<AddTodoUseCase> {
        AddTodoUseCase(repo = get())
    }

    factory<DeleteTodoUseCase> {
        DeleteTodoUseCase(repo = get())
    }

    factory<UpdateTodoUseCase> {
        UpdateTodoUseCase(repo = get())
    }
}