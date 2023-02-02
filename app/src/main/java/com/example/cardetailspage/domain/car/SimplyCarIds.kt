package com.example.cardetailspage.domain.car

import com.example.cardetailspage.repository.car.CarIdsRepo

class SimplyCarIds(private val carIdsRepo: CarIdsRepo): CarIdsUseCase {
    override suspend fun getIds() = carIdsRepo.getIds()
}