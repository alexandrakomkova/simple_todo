package com.example.simple_todo.domain.use_cases

import com.example.simple_todo.data.repository.FakeTodoRepo
import com.example.simple_todo.domain.model.TodoEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UpdateTodoUseCaseTest {
		private lateinit var getTodosUseCase: GetTodosUseCase
		private lateinit var updateTodoUseCase: UpdateTodoUseCase
		private lateinit var fakeTodoRepo: FakeTodoRepo
		@BeforeEach
		fun setUp() {
				fakeTodoRepo = FakeTodoRepo()
				getTodosUseCase = GetTodosUseCase(fakeTodoRepo)
				updateTodoUseCase = UpdateTodoUseCase(fakeTodoRepo)

				val todosToInsert = mutableListOf<TodoEntity>()
				('a'..'z').forEachIndexed { index, c ->
						todosToInsert.add(
								TodoEntity(
										title = c.toString(),
										description = c.toString(),
										timestamp = index.toLong(),
										isDone = index % 2 == 0
								)
						)
				}

				runBlocking {
						todosToInsert.forEach { fakeTodoRepo.addTodo(it) }
				}

		}

		@OptIn(ExperimentalCoroutinesApi::class)
		@Test
		fun changeTodoStatus() = runBlocking<Unit> {
				var todoOld = getTodosUseCase.invoke().flatMapConcat { it.asFlow() }.toList().first()

				updateTodoUseCase.invoke(todoOld.copy(isDone = !todoOld.isDone))

				val todoNew = getTodosUseCase.invoke().flatMapConcat { it.asFlow() }.toList().first()

				assertThat(todoOld != todoNew)
		}


}