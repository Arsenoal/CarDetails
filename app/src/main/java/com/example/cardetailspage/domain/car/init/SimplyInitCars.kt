package com.example.cardetailspage.domain.car.init

import com.example.cardetailspage.repository.car.init.InitCarsLocalDB
import com.example.cardetailspage.repository.model.car.CarDetailsRoom

class SimplyInitCars(
    private val initCarsLocalDB: InitCarsLocalDB
): InitCarsUseCase {
    override suspend fun addAll(cars: List<CarDetailsRoom>) =
        initCarsLocalDB.addAll(cars)
}