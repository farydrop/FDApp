package com.example.fdapp.di

import com.example.fdapp.viewmodel.MainViewModel
import com.example.fdapp.viewmodel.SecondViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SecondViewModel() }
}