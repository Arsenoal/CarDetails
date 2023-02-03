package com.example.cardetailspage.domain.car.cardetails

import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.repository.car.carDetails.CarDetailsRepo

class SimplyCarDetails(private val carDetailsRepo: CarDetailsRepo): CarDetailsUseCase {

    override suspend fun getCarDetails(id: String) =
        carDetailsRepo.getCarDetails(id)
    override suspend fun changeDoorState(id: String, newState: DoorState) =
        carDetailsRepo.changeDoorState(id, newState)

    override suspend fun getDateUpdated(id: String) = carDetailsRepo.getDateUpdated(id)
    override suspend fun resetUpdateDate(id: String) = carDetailsRepo.resetUpdateDate(id)
}