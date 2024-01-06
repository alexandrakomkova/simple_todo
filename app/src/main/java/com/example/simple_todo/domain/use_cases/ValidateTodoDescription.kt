package com.example.simple_todo.domain.use_cases

class ValidateTodoDescription {
    fun execute(desc: String): ValidationResult {

        // actually it can be :) like why not?
        // but let's save this validation class for future purposes

        /* if(desc.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Description should not be blank"
            )
        } */

        return ValidationResult(
            successful = true
        )
    }
}