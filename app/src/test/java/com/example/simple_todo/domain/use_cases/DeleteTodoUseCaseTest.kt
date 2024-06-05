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
class DeleteTodoUseCaseTest {
		private lateinit var getTodosUseCase: GetTodosUseCase
		private lateinit var deleteTodoUseCase: DeleteTodoUseCase
		private lateinit var fakeTodoRepo: FakeTodoRepo

		@BeforeEach
		fun setUp() {
				fakeTodoRepo = FakeTodoRepo()
				deleteTodoUseCase = DeleteTodoUseCase(fakeTodoRepo)
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
		fun deleteAllTodos_checkDbIsEmpty() = runBlocking<Unit> {
				val todos = getTodosUseCase.invoke().flatMapConcat { it.asFlow() }.toList()

				todos.map { deleteTodoUseCase(it) }

				assertThat(todos.isEmpty())
		}
}