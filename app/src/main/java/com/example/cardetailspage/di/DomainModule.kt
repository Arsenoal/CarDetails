package com.example.cardetailspage.di

import com.example.cardetailspage.domain.car.CarIdsUseCase
import com.example.cardetailspage.domain.car.SimplyCarIds
import com.example.cardetailspage.domain.car.cardetails.*
import com.example.cardetailspage.domain.car.init.InitCarsUseCase
import com.example.cardetailspage.domain.car.init.InitStateUseCase
import com.example.cardetailspage.domain.car.init.SimplyInitCars
import com.example.cardetailspage.domain.car.init.SimplyInitState
import org.koin.dsl.module

val DomainModule = module {
    single<CarIdsUseCase> { SimplyCarIds(get()) }

    single<CarFuelUseCase> { SimplyCarFuel(get()) }

    single<CarDoorsUseCase> { SimplyCarDoors(get()) }

    single<CarDetailsUseCase> { SimplyCarDetails(get()) }

    single<InitCarsUseCase> { SimplyInitCars(get()) }

    single<InitStateUseCase> { SimplyInitState(get()) }
}