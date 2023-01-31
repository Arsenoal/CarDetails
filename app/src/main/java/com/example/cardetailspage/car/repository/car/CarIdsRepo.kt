package com.example.cardetailspage.car.repository.car

interface CarIdsRepo {
    suspend fun getIds(): List<String>
}