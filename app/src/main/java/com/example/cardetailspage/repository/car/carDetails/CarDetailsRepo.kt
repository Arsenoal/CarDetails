package com.example.cardetailspage.repository.car.carDetails

interface CarDetailsRepo {
    suspend fun getCarName(id: String): String
    suspend fun getCarImage(id: String): String
    suspend fun getDateUpdated(id: String): Long
    suspend fun resetUpdateDate(id: String)
}