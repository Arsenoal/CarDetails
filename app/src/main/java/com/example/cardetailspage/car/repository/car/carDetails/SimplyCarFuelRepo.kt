package com.example.cardetailspage.car.repository.car.carDetails

import com.example.cardetailspage.car.repository.model.Mock

class SimplyCarFuelRepo: CarFuelRepo {
    override suspend fun getFuelLeft(id: String) = Mock.CAR_FUEL[id] ?: 0L
}