package com.dipumba.movies.android.di

import com.dipumba.movies.android.presentation.screens.home.HomeVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { HomeVM(get()) }
}
