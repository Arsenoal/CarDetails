package com.example.cardetailspage.di

import com.example.cardetailspage.repository.car.CarIdsRepo
import com.example.cardetailspage.repository.car.SimplyCarIdsRepo
import com.example.cardetailspage.repository.car.carDetails.*
import com.example.cardetailspage.repository.car.init.InitCarsLocalDB
import com.example.cardetailspage.repository.car.init.InitStateRepo
import com.example.cardetailspage.repository.car.init.SimplyInitCars
import com.example.cardetailspage.repository.car.init.SimplyInitState
import org.koin.dsl.module

val RepoModule = module {
    single<CarIdsRepo> { SimplyCarIdsRepo(get()) }

    single<CarDetailsRepo> { SimplyCarDetailsRepo(get()) }

    single<CarDoorsRepo> { SimplyCarDoorsRepo(get()) }

    single<CarFuelRepo> { SimplyCarFuelRepo(get()) }

    single<InitCarsLocalDB> { SimplyInitCars(get()) }

    single<InitStateRepo> { SimplyInitState(get()) }
}