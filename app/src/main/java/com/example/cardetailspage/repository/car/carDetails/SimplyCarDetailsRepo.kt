package com.example.cardetailspage.repository.car.carDetails

import com.example.cardetailspage.repository.service.room.CarDetailsDao

class SimplyCarDetailsRepo(
    private val carDetailsDao: CarDetailsDao
): CarDetailsRepo {
    override suspend fun getCarName(id: String) = carDetailsDao.getCarName(id)
    override suspend fun getCarImage(id: String) = carDetailsDao.getCarImage(id)
    override suspend fun getDateUpdated(id: String) = carDetailsDao.getDateUpdated(id)
    override suspend fun resetUpdateDate(id: String) = carDetailsDao.updateCarById(carId = id, System.currentTimeMillis())
}