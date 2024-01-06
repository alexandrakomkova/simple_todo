package com.example.simple_todo.presentation.add_todo_form

sealed class AddTodoFormEvent {
    data class TodoTitleChanged(val title: String): AddTodoFormEvent()
    data class TodoDescChanged(val desc: String): AddTodoFormEvent()
    data class TodoDialogOpened(val dialogOpened: Boolean): AddTodoFormEvent()

    object Submit: AddTodoFormEvent()
}
