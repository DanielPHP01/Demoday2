package com.dipumba.movies.android.remote

import com.dipumba.movies.android.remote.model.SignUpDto
import com.dipumba.movies.android.network.result.Resource
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register/")
    suspend fun signUp(@Body signUpDto: SignUpDto): Resource<SignUpDto>
}