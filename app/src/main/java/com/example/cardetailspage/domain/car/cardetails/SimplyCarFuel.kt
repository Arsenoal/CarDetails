package com.example.cardetailspage.domain.car.cardetails

import com.example.cardetailspage.repository.car.carDetails.CarFuelRepo

class SimplyCarFuel(private val carFuelRepo: CarFuelRepo): CarFuelUseCase {
    override suspend fun getFuelLeft(id: String) = carFuelRepo.getFuelLeft(id)
}