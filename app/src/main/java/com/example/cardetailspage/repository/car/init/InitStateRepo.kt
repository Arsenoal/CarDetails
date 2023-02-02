package com.example.cardetailspage.repository.car.init

import com.example.cardetailspage.repository.model.car.DBInitState

interface InitStateRepo {
    suspend fun isInitialized(): Boolean

    suspend fun setInitialized(state: DBInitState)
}