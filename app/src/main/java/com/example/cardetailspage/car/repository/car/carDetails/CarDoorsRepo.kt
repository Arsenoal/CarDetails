package com.example.cardetailspage.car.repository.car.carDetails

import com.example.cardetailspage.entity.car.DoorState

interface CarDoorsRepo {
    suspend fun getCarDoorsState(id: String): DoorState

    suspend fun changeState(id: String, newState: DoorState)
}