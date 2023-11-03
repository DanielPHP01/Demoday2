package com.dipumba.movies.android.presentation.screens.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dipumba.movies.android.R
import com.dipumba.movies.android.TextFieldColorPlaceHolder
import com.dipumba.movies.android.network.result.Resource
import com.dipumba.movies.android.remote.model.SignUpDto
import org.koin.androidx.compose.koinViewModel

@Composable
fun Home(
    context: Context,
    navigationVerified: () -> Unit
) {
    val vm: HomeVM = koinViewModel()
    var nameText by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var againPassword by remember { mutableStateOf("") }

    fun initViewModel() {
        val signUpDto = SignUpDto(username = nameText, password = password)

        if (password == againPassword && password.length in 8..20) {
            vm.signUp(signUpDto).observeForever {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        navigationVerified.invoke()
                    }

                    Resource.Status.ERROR -> {
                        Toast.makeText(
                            context,
                            "Пользователь уже зарегистрирован",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {
                        Toast.makeText(
                            context,
                            "Пользователь уже зарегистрирован",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        } else {
            Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
        }
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
                .width(325.dp)
                .height(400.dp)
                .background(
                    color = Color.LightGray.copy(alpha = 0.65f)
                ),
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
                    .background(color = Color.Transparent),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Регистрация",
                    fontSize = 20.sp,
                    color = Color.Black
                )
                TextField(
                    value = nameText,
                    onValueChange = { nameText = it },
                    colors = TextFieldDefaults.textFieldColors(Color.Black),
                    placeholder = {
                        Text(
                            text = "Логин",
                            color = TextFieldColorPlaceHolder,
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))

                )
                var showPassword by remember { mutableStateOf(value = false) }

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    colors = TextFieldDefaults.textFieldColors(Color.Black),
                    placeholder = {
                        Text(
                            text = "Пароль",
                            color = TextFieldColorPlaceHolder
                        )
                    },
                    visualTransformation = if (showPassword) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        if (showPassword) {
                            IconButton(onClick = { showPassword = false }) {
                                Icon(
                                    imageVector = Icons.Filled.Visibility,
                                    contentDescription = "hide_password",
                                    tint = Color.Unspecified

                                )
                            }
                        } else {
                            IconButton(
                                onClick = { showPassword = true }) {
                                Icon(
                                    imageVector = Icons.Filled.VisibilityOff,
                                    contentDescription = "hide_password",
                                    tint = Color.Unspecified

                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))

                )
                var showAgainPassword by remember { mutableStateOf(value = false) }

                TextField(
                    value = againPassword,
                    onValueChange = { againPassword = it },
                    colors = TextFieldDefaults.textFieldColors(Color.Black),
                    placeholder = {
                        Text(
                            text = "Повторите пароль",
                            color = TextFieldColorPlaceHolder
                        )
                    },
                    visualTransformation = if (showAgainPassword) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        if (showAgainPassword) {
                            IconButton(onClick = { showAgainPassword = false }) {
                                Icon(
                                    imageVector = Icons.Filled.Visibility,
                                    contentDescription = "hide_password",
                                    tint = Color.Unspecified

                                )
                            }
                        } else {
                            IconButton(
                                onClick = { showAgainPassword = true }) {
                                Icon(
                                    imageVector = Icons.Filled.VisibilityOff,
                                    contentDescription = "hide_password",
                                    tint = Color.Unspecified
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp)),
                )

                Button(
                    onClick = { initViewModel() },
                    colors = ButtonDefaults.buttonColors(Color(0xFF2E8419)),
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = "Регистрация",
                        fontSize = 15.sp,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }
        }
    }
}