package com.dipumba.movies.android.di

import com.dipumba.movies.android.repository.Repository
import org.koin.dsl.module

val repoModules = module {
    single { Repository(get()) }
}

