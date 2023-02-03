package com.example.cardetailspage.domain.car.cardetails

import com.example.cardetailspage.entity.car.CarDetails
import com.example.cardetailspage.entity.car.DoorState

interface CarDetailsUseCase {
    suspend fun getCarDetails(id: String): CarDetails
    suspend fun changeDoorState(id: String, newState: DoorState)
    suspend fun getDateUpdated(id: String): Long
    suspend fun resetUpdateDate(id: String)
}