package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.UserDataUiEvents
import com.example.myapplication.ui.TextComponent
import com.example.myapplication.ui.TextFieldComponent
import com.example.myapplication.ui.TopBar
import com.example.myapplication.ui.UserInputViewModel

@Composable
fun UserInputScreen(userInputViewModel: UserInputViewModel) {
    Surface (modifier = Modifier.fillMaxSize()){
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)){
            TopBar("Hi there \uD83D\uDE0A")

            TextComponent(textValue = "Let's learn about You!",
                textSize = 24.sp)

            Spacer(modifier = Modifier.size(20.dp))

            TextComponent(textValue = "This app will prepare a details page based on input provided by you !",
                textSize = 18.sp)

            Spacer(modifier = Modifier.size(60.dp))

            TextFieldComponent(onTextChanged = {
                userInputViewModel.onEvent(
                    UserDataUiEvents.UserNameEntered(it)
                )
            })
        }
    }
}

@Preview
@Composable
fun UserInputScreenPreview(){
    UserInputScreen(UserInputViewModel())
}