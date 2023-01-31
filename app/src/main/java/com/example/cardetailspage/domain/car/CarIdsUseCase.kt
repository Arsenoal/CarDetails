package com.example.cardetailspage.domain.car

interface CarIdsUseCase {
    suspend fun getIds(): List<String>
}