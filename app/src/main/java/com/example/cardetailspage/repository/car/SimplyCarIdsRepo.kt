package com.example.cardetailspage.repository.car

import com.example.cardetailspage.repository.service.room.CarDetailsDao

class SimplyCarIdsRepo(private val carDetailsDao: CarDetailsDao): CarIdsRepo {
    override suspend fun getIds() = carDetailsDao.getCarIds()
}