package com.example.simple_todo.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

//val Purple80 = Color(0xFFD0BCFF)
//val PurpleGrey80 = Color(0xFFCCC2DC)
//val Pink80 = Color(0xFFEFB8C8)
//
//val Purple40 = Color(0xFF6650a4)
//val PurpleGrey40 = Color(0xFF625b71)
//val Pink40 = Color(0xFF7D5260)


val Dark200 = Color(0xff44475a)
val Dark500 = Color(0xff2d2f3c)


val DarkBackground = Color(0xff232128)
val Background = Color(0xff2C2A33)

val Green80 = Color(0xffB7F1BA)
val Blue80 = Color(0xffB7C0E2)
val Red80 = Color(0xffDF5C64)
val Purple60 = Color(0xffB082E0)
val White80 = Color(0xffF1F6FD)

val brushDone = Brush.horizontalGradient(listOf(Green80, Blue80)) // try linearGradient
val brushNotDone = Brush.horizontalGradient(listOf(Red80, Purple60))