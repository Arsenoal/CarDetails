package com.example.cardetailspage.car.repository.car

import com.example.cardetailspage.car.repository.model.Mock

class SimplyCarIdsRepo: CarIdsRepo {
    override suspend fun getIds() = Mock.CAR_IDS
}