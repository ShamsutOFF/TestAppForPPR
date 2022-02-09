package com.example.testappforppr.di

import com.example.testappforppr.model.NumbersRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { createRepository() }
}
fun createRepository () : NumbersRepositoryImpl = NumbersRepositoryImpl()