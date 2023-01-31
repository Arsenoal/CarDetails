package com.example.cardetailspage.car.repository.car.carDetails

interface CarFuelRepo {
    suspend fun getFuelLeft(id: String): Long
}