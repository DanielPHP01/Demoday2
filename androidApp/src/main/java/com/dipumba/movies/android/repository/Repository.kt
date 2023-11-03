package com.dipumba.movies.android.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dipumba.movies.android.remote.ApiService
import com.dipumba.movies.android.remote.model.SignUpDto
import com.dipumba.movies.android.network.result.Resource
import kotlinx.coroutines.Dispatchers

class Repository(private val apiService: ApiService) {

    fun signUp(signUpDto: SignUpDto): LiveData<Resource<SignUpDto>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            try {
                val response = apiService.signUp(signUpDto)
                if (response.code == 400) {
                    emit(Resource.error("Invalid request", null))
                } else {
                    emit(Resource.success(response.data))
                }
            } catch (e: Exception) {
                emit(Resource.error("Server error", null))
            }
        }
    }
}