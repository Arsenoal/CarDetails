package com.example.cardetailspage.domain.car.cardetails

import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.repository.car.carDetails.CarDoorsRepo

class SimplyCarDoors(private val carDoorsRepo: CarDoorsRepo): CarDoorsUseCase {
    override suspend fun getCarDoorsState(id: String) = carDoorsRepo.getCarDoorsState(id)

    override suspend fun changeState(id: String, newState: DoorState) = carDoorsRepo.changeState(id, newState)
}