package com.example.cardetailspage.domain.car.cardetails

import com.example.cardetailspage.entity.car.DoorState

interface CarDoorsUseCase {
    suspend fun getCarDoorsState(id: String): DoorState

    suspend fun changeState(id: String, newState: DoorState)
}