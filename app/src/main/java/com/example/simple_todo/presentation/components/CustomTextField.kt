package com.example.simple_todo.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.simple_todo.ui.theme.Red80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    labelText: String,
    title: String,
    setTitle: (String) -> Unit,
    maxLines: Int,
    hasError: Boolean,
    errorMessage: String?
) {
    Column (
        verticalArrangement = Arrangement.Center
            ) {
        OutlinedTextField(
            value = title,
            onValueChange = { setTitle(it) },
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(text = labelText)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                focusedLabelColor = Color.White,
                textColor = Color.White
            ),
            maxLines = maxLines,
            shape = RoundedCornerShape(15.dp),
            isError = hasError

        )
        Spacer(modifier = Modifier.height(5.dp))
        if(hasError) {
            Text(
                text = errorMessage?: "",
                color = Red80,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp)
            )
        }
    }
}