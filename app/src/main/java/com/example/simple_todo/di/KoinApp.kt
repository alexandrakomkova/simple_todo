package com.example.simple_todo.di

import android.app.Application
import androidx.room.Room
import com.example.simple_todo.data.data_source.TodoDatabase
import com.example.simple_todo.data.repository.TodoRepoImpl
import com.example.simple_todo.domain.repository.TodoRepo
import com.example.simple_todo.domain.use_cases.ValidateTodoDescription
import com.example.simple_todo.domain.use_cases.ValidateTodoTitle
import com.example.simple_todo.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

class KoinApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(module {
                single {
                    Room
                        .databaseBuilder(this@KoinApp, TodoDatabase::class.java, "db")
                        .build()
                }
                single {
                    TodoRepoImpl(database = get())
                } bind TodoRepo::class

                single { ValidateTodoTitle() } //??
                single { ValidateTodoDescription() } //??

                viewModel { HomeViewModel() }

            })
        }
    }
}

