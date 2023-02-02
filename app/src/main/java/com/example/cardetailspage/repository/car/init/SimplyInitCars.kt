package com.example.cardetailspage.repository.car.init

import com.example.cardetailspage.repository.model.car.CarDetailsRoom
import com.example.cardetailspage.repository.service.room.CarDetailsDao

class SimplyInitCars(
    private val carsDao: CarDetailsDao
): InitCarsLocalDB {
    override suspend fun addAll(cars: List<CarDetailsRoom>) {
        carsDao.insertAll(cars)
    }
}