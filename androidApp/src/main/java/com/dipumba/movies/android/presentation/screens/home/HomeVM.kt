package com.dipumba.movies.android.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dipumba.movies.android.network.result.Resource
import com.dipumba.movies.android.network.ui.BaseViewModel
import com.dipumba.movies.android.remote.model.SignUpDto
import com.dipumba.movies.android.repository.Repository


class HomeVM(private val repository: Repository) : BaseViewModel() {

    fun signUp(signUpDto: SignUpDto): LiveData<Resource<SignUpDto>> {
        return repository.signUp(signUpDto)
    }
}