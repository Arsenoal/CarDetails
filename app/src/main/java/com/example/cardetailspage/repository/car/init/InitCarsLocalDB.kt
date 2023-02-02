package com.example.cardetailspage.repository.car.init

import com.example.cardetailspage.repository.model.car.CarDetailsRoom

interface InitCarsLocalDB {
    suspend fun addAll(cars: List<CarDetailsRoom>)
}