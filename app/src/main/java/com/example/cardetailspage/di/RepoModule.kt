package com.example.cardetailspage.di

import com.example.cardetailspage.car.repository.car.CarIdsRepo
import com.example.cardetailspage.car.repository.car.SimplyCarIdsRepo
import com.example.cardetailspage.car.repository.car.carDetails.*
import org.koin.dsl.module

val RepoModule = module {
    single<CarIdsRepo> { SimplyCarIdsRepo() }

    single<CarDetailsRepo> { SimplyCarDetailsRepo() }

    single<CarDoorsRepo> { SimplyCarDoorsRepo() }

    single<CarFuelRepo> { SimplyCarFuelRepo() }
}