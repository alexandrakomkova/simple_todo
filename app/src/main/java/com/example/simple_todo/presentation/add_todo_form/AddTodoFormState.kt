package com.example.simple_todo.presentation.add_todo_form

data class AddTodoFormState(
    val title: String = "",
    val titleError: String? = null,
    val description: String = "",
    val descriptionError: String? = null,
    val dialogOpen: Boolean = false
)