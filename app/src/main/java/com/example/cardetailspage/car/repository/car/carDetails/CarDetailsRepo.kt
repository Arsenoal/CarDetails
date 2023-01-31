package com.example.cardetailspage.car.repository.car.carDetails

interface CarDetailsRepo {
    suspend fun getCarName(id: String): String

    suspend fun getCarImage(id: String): String
}