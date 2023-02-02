package com.example.cardetailspage.domain.car.init

import com.example.cardetailspage.repository.model.car.DBInitState

interface InitStateUseCase {
    suspend fun isInitialized(): Boolean

    suspend fun setInitialized(state: DBInitState)
}