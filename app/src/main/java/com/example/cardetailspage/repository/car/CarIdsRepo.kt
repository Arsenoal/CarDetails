package com.example.cardetailspage.repository.car

interface CarIdsRepo {
    suspend fun getIds(): List<String>
}