package com.example.simple_todo.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.simple_todo.presentation.MainActivity
import com.example.simple_todo.ui.theme.Simple_todoTheme
import org.junit.Assert.*
import org.junit.Rule
// import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.junit.Test
import org.koin.androidx.compose.koinViewModel
@RunWith(AndroidJUnit4::class)
class HomeScreenKtTest {
		@get:Rule
		val ruleActivity = createAndroidComposeRule<MainActivity>()

		@Test
		fun tapAddButton_addFormAppeared() {
				/*rule.setContent {
						Simple_todoTheme {
								Surface(
										modifier = Modifier.fillMaxSize(),
										color = MaterialTheme.colorScheme.background
								) {
										val viewModel: HomeViewModel = koinViewModel()
										val state = viewModel.state
										val context = LocalContext.current

										HomeScreen(
												viewModel = viewModel,
												state = state,
												onEvent = viewModel::onEvent,
												context = context
										)
								}
						}
				}*/

				ruleActivity.onNodeWithContentDescription("add_todo_btn").performClick()
				ruleActivity.onNodeWithText("Title").assertIsDisplayed()
		}



		@Test
		fun fillAddForm_correct() {

				val title = "ui test"
				val desc = "ui test description"

				// open add todos dialog
				ruleActivity.onNodeWithContentDescription("add_todo_btn").performClick()

				// fill fields
				ruleActivity.onNodeWithText("Title").performTextInput(title)
				ruleActivity.onNodeWithText("Description").performTextInput(desc)

				// check if fields are empty
				ruleActivity.onNodeWithText("Title").assertTextContains(title)
				ruleActivity.onNodeWithText("Description").assertTextContains(desc)
		}

		@Test
		fun fillAddForm_error() {

				// open add todos dialog
				ruleActivity.onNodeWithContentDescription("add_todo_btn").performClick()

				// save
				ruleActivity.onNodeWithText("Save").performClick()

				//check error label
				ruleActivity.onNodeWithText("Title should not be blank").assertIsDisplayed()
		}
}