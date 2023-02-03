package com.example.cardetailspage.repository.car.carDetails

import com.example.cardetailspage.entity.car.CarDetails
import com.example.cardetailspage.entity.car.DoorState

interface CarDetailsRepo {

    suspend fun getCarDetails(id: String): CarDetails
    suspend fun changeDoorState(id: String, newState: DoorState)
    suspend fun getDateUpdated(id: String): Long
    suspend fun resetUpdateDate(id: String)
}