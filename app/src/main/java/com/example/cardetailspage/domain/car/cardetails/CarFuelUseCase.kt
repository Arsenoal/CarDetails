package com.example.cardetailspage.domain.car.cardetails

interface CarFuelUseCase {
    suspend fun getFuelLeft(id: String): Long
}