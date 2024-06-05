package com.example.simple_todo.domain.use_cases

import com.example.simple_todo.data.repository.FakeTodoRepo
import com.example.simple_todo.domain.model.TodoEntity
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AddTodoUseCaseTest {
		private lateinit var getTodosUseCase: GetTodosUseCase
		private lateinit var addTodoUseCase: AddTodoUseCase
		private lateinit var fakeTodoRepo: FakeTodoRepo

		@BeforeEach
		fun setUp() {

				fakeTodoRepo = FakeTodoRepo()
				getTodosUseCase = GetTodosUseCase(fakeTodoRepo)
			  addTodoUseCase = AddTodoUseCase(fakeTodoRepo)
		}

		@Test
		fun addTodo_correct() = runBlocking<Unit> {
				('a'..'z').forEachIndexed { index, c ->
						addTodoUseCase.invoke(
								TodoEntity(
										title = c.toString(),
										description = c.toString(),
										timestamp = index.toLong(),
										isDone = index % 2 == 0
								)
						)

				}

				val todos = getTodosUseCase.invoke().flatMapConcat { it.asFlow() }.toList()
				Truth.assertThat(todos.isNotEmpty())
		}

		@Test
		fun addEmptyTitleTodo_validationCheck() = runBlocking<Unit> {
				val todo = TodoEntity(
						title = "",
						description = "123",
						timestamp = 1.toLong(),
						isDone = false
				)

				val titleResult = ValidateTodoTitle().execute(todo.title)

				Assertions.assertFalse(titleResult.successful)
		}


}