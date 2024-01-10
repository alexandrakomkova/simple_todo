package com.example.simple_todo.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simple_todo.domain.model.TodoEntity
import com.example.simple_todo.domain.model.addDate
import com.example.simple_todo.ui.theme.Background
import com.example.simple_todo.ui.theme.DarkBackground
import com.example.simple_todo.ui.theme.brushDone
import com.example.simple_todo.ui.theme.brushNotDone


@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TodoItem(
    todo: TodoEntity,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    val color by animateColorAsState(
        targetValue = if (todo.isDone) Color.Green else Color.Red,
        animationSpec = tween(500),
        label = "brush"
    )

    val cardColor = if (todo.isDone) brushDone else brushNotDone


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateItemPlacement(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        contentAlignment = Alignment.BottomEnd
    )  {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(cardColor)
                .clickable { onClick() }
                .padding(
                    horizontal = 14.dp,
                    vertical = 24.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {



            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Box (
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Background)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        AnimatedVisibility(
                            visible = todo.isDone,
                            enter =  scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut()
                        ) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = null,
                                tint = color,
                                modifier = Modifier.size(128.dp)
                            )
                        }
                    }
                    Row {
                        AnimatedVisibility(
                            visible = !todo.isDone,
                            enter =  scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut()
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = null,
                                tint = color,
                                modifier = Modifier.size(128.dp)
                            )
                        }
                    }
                }
                Column(modifier = Modifier.padding(start =  4.dp)) {
                    Text(
                        text = todo.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = DarkBackground
                    )
                    Text(
                        text = todo.description,
                        fontSize = 12.sp,
                        color = DarkBackground
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Background)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Delete,
                    tint = Color.White,
                    contentDescription = null,
                    modifier = Modifier
                        .size(128.dp)
                        .clickable { onDelete() }
                )
            }

        }
        Text(
            modifier = Modifier
                .padding(
                    horizontal = 14.dp,
                    vertical = 8.dp
                ),
            text = todo.addDate,
            color = DarkBackground,
            fontSize = 10.sp
        )

    }
}