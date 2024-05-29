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
@OptIn(ExperimentalCoroutinesApi::class)
class GetTodosUseCaseTest {

		private lateinit var getTodosUseCase: GetTodosUseCase
		private lateinit var fakeTodoRepo: FakeTodoRepo

		@BeforeEach
		fun setUp() {
				fakeTodoRepo = FakeTodoRepo()
				getTodosUseCase = GetTodosUseCase(fakeTodoRepo)

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


		@Test
		fun `Check todos size, correct output`() = runBlocking<Unit> {
				 val todos = getTodosUseCase.invoke().flatMapConcat { it.asFlow() }.toList()
				 assertThat(todos.isNotEmpty())

		}

		@Test
		fun `Check first todo title, correct output`() = runBlocking<Unit> {
				val todo = getTodosUseCase.invoke().toList().first()
				assertThat(todo[0].title == "a")
		}


}