package com.example.cardetailspage.domain.car.cardetails

import com.example.cardetailspage.car.repository.car.carDetails.CarDetailsRepo

class SimplyCarDetails(private val getCarDetailsRepo: CarDetailsRepo): CarDetailsUseCase {
    override suspend fun getCarName(id: String) = getCarDetailsRepo.getCarName(id)

    override suspend fun getCarImage(id: String) = getCarDetailsRepo.getCarImage(id)
}