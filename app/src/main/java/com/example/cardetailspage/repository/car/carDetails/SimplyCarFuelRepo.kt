package com.example.cardetailspage.repository.car.carDetails

import com.example.cardetailspage.repository.service.room.CarDetailsDao

class SimplyCarFuelRepo(
    private val carDetailsDao: CarDetailsDao
): CarFuelRepo {
    override suspend fun getFuelLeft(id: String) = carDetailsDao.getCarFuel(id)
}