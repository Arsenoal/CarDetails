package com.example.cardetailspage.repository.car.init

import com.example.cardetailspage.repository.model.car.DBInitState
import com.example.cardetailspage.repository.service.room.DbInitDao

class SimplyInitState(
    private val dbInitDao: DbInitDao
): InitStateRepo {
    override suspend fun isInitialized() =
        dbInitDao.isInitialized()

    override suspend fun setInitialized(state: DBInitState) =
        dbInitDao.setInitialized(state)
}