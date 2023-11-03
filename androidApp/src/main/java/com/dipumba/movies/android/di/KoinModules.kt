package com.dipumba.movies.android.di

import org.koin.core.module.Module

val koinModules = listOf<Module>(
    repoModules,
    viewModules,
    networkModule
)