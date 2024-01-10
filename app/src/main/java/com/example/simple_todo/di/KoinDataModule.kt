package com.example.simple_todo.di

import androidx.room.Room
import com.example.simple_todo.data.data_source.TodoDatabase
import com.example.simple_todo.data.repository.TodoRepoImpl
import com.example.simple_todo.domain.repository.TodoRepo
import com.example.simple_todo.domain.use_cases.ValidateTodoDescription
import com.example.simple_todo.domain.use_cases.ValidateTodoTitle
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    single {
        Room
            .databaseBuilder(context = get(), TodoDatabase::class.java, "db")
            .build()
    }
    single {
        TodoRepoImpl(database = get())
    } bind TodoRepo::class

    single { ValidateTodoTitle() }
    single { ValidateTodoDescription() }
}