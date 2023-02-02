package com.example.cardetailspage.di

import com.example.cardetailspage.car.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {
    viewModel {
        MainViewModel(get(), get(), get(), get(), get(), get())
    }
}