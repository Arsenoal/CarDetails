package com.example.cardetailspage.car.repository.car.carDetails

import com.example.cardetailspage.car.repository.model.Mock

class SimplyCarDetailsRepo: CarDetailsRepo {
    override suspend fun getCarName(id: String) = Mock.CAR_NAME[id] ?: "No name with id: $id"

    override suspend fun getCarImage(id: String) = Mock.CAR_IMAGE[id] ?: "No image with id: $id"
}