package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.UserDataUiEvents
import com.example.myapplication.ui.ButtonComponent
import com.example.myapplication.ui.TextComponent
import com.example.myapplication.ui.TextFieldComponent
import com.example.myapplication.ui.TopBar
import com.example.myapplication.ui.UserInputViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
@Composable
fun UserInputScreen(userInputViewModel: UserInputViewModel,
                    showWelcomeScreen: (valuesPair: Pair<String, String>) -> Unit
){
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

            TextComponent(textValue = "Name", textSize = 18.sp)
            Spacer(modifier = Modifier.size(10.dp))


            TextFieldComponent(onTextChanged = {
                userInputViewModel.onEvent(
                    UserDataUiEvents.UserNameEntered(it)
                )
            })

            Spacer(modifier = Modifier.size(20.dp))

            TextComponent(textValue = "What do you like", textSize = 18.sp)



            Row (modifier = Modifier.fillMaxWidth()){
                AnimalCard(image = R.drawable.gato2, animalSelected = {
                    userInputViewModel.onEvent(
                        UserDataUiEvents.AnimalSelected(it)
                    )

                }, selected =  userInputViewModel.uiState.value.animalSelected == "Cat")
                AnimalCard(image = R.drawable.perro, animalSelected = {
                    userInputViewModel.onEvent(
                        UserDataUiEvents.AnimalSelected(it)
                    )


                },
                    selected = userInputViewModel.uiState.value.animalSelected == "Dog")


            }


            Spacer(modifier =  Modifier.weight(1f))
            //Connection to database
            if(userInputViewModel.isValidState()){
                ButtonComponent(
                    goToDetailsScreen = {
                        val nameEntered = userInputViewModel.uiState.value.nameEntered
                        val animalSelected = userInputViewModel.uiState.value.animalSelected
                        if (nameEntered.isNotBlank() && animalSelected.isNotBlank()) {
                            val database = Firebase.database
                            val myRef = database.getReference("message")
                            val userData = mapOf(
                                "nom" to nameEntered,
                                "anim" to animalSelected
                            )
                            myRef.setValue(userData)
                            showWelcomeScreen(Pair(nameEntered, animalSelected))
                        }
                    }
                )
            }
        }
    }
}
@Composable
fun AnimalCard(
    image: Int,
    selected: Boolean,
    animalSelected: (animalName: String) -> Unit
) {
    val localFocusModifier = LocalFocusManager.current

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(24.dp)
            .size(130.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    width = 1.dp,
                    color = if (selected) Color.Green else Color.Transparent,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    val animalName = if (image == R.drawable.gato2) "Cat" else "Dog"
                    animalSelected(animalName)
                    localFocusModifier.clearFocus()
                }
        ) {
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentWidth()
                    .wrapContentHeight(),
                painter = painterResource(id = image),
                contentDescription = "Animal Image"
            )
        }
    }
}