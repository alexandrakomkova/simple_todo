package com.example.simple_todo.di

import com.example.simple_todo.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<HomeViewModel> {
        HomeViewModel(
            todoUseCases = get()
        )
    }
}