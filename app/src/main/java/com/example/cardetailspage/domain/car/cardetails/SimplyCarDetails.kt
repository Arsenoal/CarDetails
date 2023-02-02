package com.example.cardetailspage.domain.car.cardetails

import com.example.cardetailspage.repository.car.carDetails.CarDetailsRepo

class SimplyCarDetails(private val getCarDetailsRepo: CarDetailsRepo): CarDetailsUseCase {
    override suspend fun getCarName(id: String) = getCarDetailsRepo.getCarName(id)
    override suspend fun getCarImage(id: String) = getCarDetailsRepo.getCarImage(id)
    override suspend fun getDateUpdated(id: String) = getCarDetailsRepo.getDateUpdated(id)
    override suspend fun resetUpdateDate(id: String) = getCarDetailsRepo.resetUpdateDate(id)
}