package com.example.simple_todo.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simple_todo.domain.model.TodoEntity
import com.example.simple_todo.domain.repository.TodoRepo
import com.example.simple_todo.domain.use_cases.ValidateTodoDescription
import com.example.simple_todo.domain.use_cases.ValidateTodoTitle
import com.example.simple_todo.presentation.add_todo_form.AddTodoFormEvent
import com.example.simple_todo.presentation.add_todo_form.AddTodoFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel: ViewModel(), KoinComponent {
    private val repo: TodoRepo by inject()

    private val _todos: MutableStateFlow<List<TodoEntity>> = MutableStateFlow(emptyList())
    val todos = _todos.asStateFlow()

    var state by mutableStateOf(AddTodoFormState())
    private val validateTodoTitle: ValidateTodoTitle by inject()
    private val validateTodoDesc: ValidateTodoDescription by inject()

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    init {
        getTodos()
    }

    fun onEvent(event: AddTodoFormEvent) {
        when(event) {
            is AddTodoFormEvent.TodoTitleChanged -> {
                state = state.copy(title = event.title)
            }
            is AddTodoFormEvent.TodoDescChanged -> {
                state = state.copy(description = event.desc)
            }
            is AddTodoFormEvent.TodoDialogOpened -> {
                state = state.copy(dialogOpen = event.dialogOpened)
            }
            is AddTodoFormEvent.Submit -> { submitData() }
        }

    }

    private fun submitData() {
        val titleResult = validateTodoTitle.execute(state.title)
        val descriptionResult = validateTodoDesc.execute(state.description)

        val hasError = listOf(
            titleResult,
            descriptionResult
        ).any { !it.successful }

        if(hasError) {
            state = state.copy(
                titleError = titleResult.errorMessage,
                descriptionError = descriptionResult.errorMessage,
                dialogOpen = true
            )
            return
        }

        viewModelScope.launch { validationEventChannel.send(ValidationEvent.Success) }
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

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }

}