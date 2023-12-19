package com.example.simple_todo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simple_todo.domain.model.TodoEntity
import com.example.simple_todo.domain.repository.TodoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel: ViewModel(), KoinComponent {
    private val repo: TodoRepo by inject()

    private val _todos: MutableStateFlow<List<TodoEntity>> = MutableStateFlow(emptyList())
    val todos = _todos.asStateFlow()

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getTodos().collect { data ->
                _todos.update { data }
            }
        }
    }

    fun addTodos(todo: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addTodo(todo)
        }
    }

    fun updateTodos(todo: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateTodo(todo)
        }
    }

    fun deleteTodos(todo: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteTodo(todo)
        }
    }

}