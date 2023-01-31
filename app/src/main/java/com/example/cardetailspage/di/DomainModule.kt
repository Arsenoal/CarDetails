package com.example.cardetailspage.di

import com.example.cardetailspage.domain.car.CarIdsUseCase
import com.example.cardetailspage.domain.car.SimplyCarIds
import com.example.cardetailspage.domain.car.cardetails.*
import org.koin.dsl.module

val DomainModule = module {
    single<CarIdsUseCase> { SimplyCarIds(get()) }

    single<CarFuelUseCase> { SimplyCarFuel(get()) }

    single<CarDoorsUseCase> { SimplyCarDoors(get()) }

    single<CarDetailsUseCase> { SimplyCarDetails(get()) }
}