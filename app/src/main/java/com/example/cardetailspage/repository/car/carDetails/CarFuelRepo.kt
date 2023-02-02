package com.example.cardetailspage.repository.car.carDetails

interface CarFuelRepo {
    suspend fun getFuelLeft(id: String): Long
}