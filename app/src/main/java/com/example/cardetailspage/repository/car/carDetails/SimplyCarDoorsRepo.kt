package com.example.cardetailspage.repository.car.carDetails

import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.repository.common.asString
import com.example.cardetailspage.repository.common.toDoorState
import com.example.cardetailspage.repository.model.Mock
import com.example.cardetailspage.repository.service.room.CarDetailsDao
import kotlinx.coroutines.delay

class SimplyCarDoorsRepo(
    private val carDetailsDao: CarDetailsDao
): CarDoorsRepo {
    override suspend fun getCarDoorsState(id: String) = carDetailsDao.getCarDoorState(id)

    override suspend fun changeState(id: String, newState: DoorState) {
        carDetailsDao.updateDoorState(id, newState)
    }
}