package com.example.simple_todo.domain.use_cases

class ValidateTodoTitle {
    fun execute(title: String): ValidationResult {
        if(title.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Title should not be blank"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}