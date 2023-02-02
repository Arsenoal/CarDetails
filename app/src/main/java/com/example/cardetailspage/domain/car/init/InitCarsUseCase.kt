package com.example.cardetailspage.domain.car.init

import com.example.cardetailspage.repository.model.car.CarDetailsRoom

interface InitCarsUseCase {
    suspend fun addAll(cars: List<CarDetailsRoom>)
}