package com.example.cardetailspage.domain.car.cardetails

interface CarDetailsUseCase {
    suspend fun getCarName(id: String): String

    suspend fun getCarImage(id: String): String
}