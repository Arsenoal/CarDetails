package com.example.cardetailspage.repository.car.carDetails

import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.repository.model.car.toCarDetails
import com.example.cardetailspage.repository.service.room.CarDetailsDao

class SimplyCarDetailsRepo(
    private val carDetailsDao: CarDetailsDao
): CarDetailsRepo {

    override suspend fun getCarDetails(id: String) =
        carDetailsDao.getCarById(id).toCarDetails()
    override suspend fun changeDoorState(id: String, newState: DoorState) =
        carDetailsDao.updateDoorState(id, newState)

    override suspend fun getDateUpdated(id: String) = carDetailsDao.getDateUpdated(id)
    override suspend fun resetUpdateDate(id: String) = carDetailsDao.updateCarById(carId = id, System.currentTimeMillis())
}