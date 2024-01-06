package com.example.simple_todo.presentation.home

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.simple_todo.domain.model.TodoEntity
import com.example.simple_todo.presentation.add_todo_form.AddTodoFormEvent
import com.example.simple_todo.presentation.add_todo_form.AddTodoFormState
import com.example.simple_todo.presentation.components.CustomTextField
import com.example.simple_todo.presentation.components.TodoItem
import com.example.simple_todo.ui.theme.Background
import com.example.simple_todo.ui.theme.DarkBackground
import com.example.simple_todo.ui.theme.White80

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HomeScreen (
    viewModel: HomeViewModel,
    state: AddTodoFormState,
    onEvent: (AddTodoFormEvent) -> Unit,
    context: Context
) {
    val todos by viewModel.todos.collectAsState()

    LaunchedEffect(context) {
        viewModel.validationEvents.collect {event ->
            when(event) {
                is HomeViewModel.ValidationEvent.Success -> {
                    viewModel.addTodos(
                        TodoEntity(
                            title = viewModel.state.title,
                            description = viewModel.state.description
                        )

                    )

                    viewModel.onEvent((AddTodoFormEvent.TodoDialogOpened(false)))
                    viewModel.onEvent(AddTodoFormEvent.TodoTitleChanged(""))
                    viewModel.onEvent(AddTodoFormEvent.TodoDescChanged(""))
                }
            }
        }
    }

    if(state.dialogOpen) {

        Dialog(onDismissRequest = {
            onEvent(AddTodoFormEvent.TodoDialogOpened(false))
        }) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Background)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                CustomTextField(
                    "Title",
                    state.title,
                    { onEvent(AddTodoFormEvent.TodoTitleChanged(it)) } ,
                    20,
                    state.titleError != null,
                   state.titleError
                )

                CustomTextField(
                    "Description",
                    state.description,
                    { onEvent(AddTodoFormEvent.TodoDescChanged(it)) },
                    20,
                    state.descriptionError != null,
                    state.descriptionError
                )

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = { onEvent(AddTodoFormEvent.Submit) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = White80
                    )
                ) {
                    Text(
                        text = "Save",
                        color = DarkBackground,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }

    Scaffold(
        containerColor = DarkBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(AddTodoFormEvent.TodoDialogOpened(true)) } ,
                shape = CircleShape,
                contentColor = DarkBackground,
                containerColor = White80
            ) {
                Icon(Icons.Default.Add, contentDescription = "add_todo_btn")

            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = todos.isEmpty(),
                enter =  scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                Text(
                    text = "Add new todo!!",
                    color = Color.White,
                    fontSize = 22.sp)
            }

            AnimatedVisibility(
                visible = todos.isNotEmpty(),
                enter =  scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = paddingValues.calculateBottomPadding() + 8.dp,
                            top = 8.dp,
                            end = 8.dp,
                            start = 8.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        todos.sortedBy { it.isDone },
                        key = { it.id }
                    ) { todo ->
                        TodoItem(
                            todo = todo,
                            onClick = { viewModel.updateTodos(todo.copy(isDone = !todo.isDone)) },
                            onDelete = { viewModel.deleteTodos(todo) }
                        )
                    }
                }
            }
        }
    }
}
