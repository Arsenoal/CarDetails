package com.example.cardetailspage.domain.car.init

import com.example.cardetailspage.repository.car.init.InitStateRepo
import com.example.cardetailspage.repository.model.car.DBInitState

class SimplyInitState(
    private val initStateRepo: InitStateRepo
): InitStateUseCase {
    override suspend fun isInitialized() =
        initStateRepo.isInitialized()

    override suspend fun setInitialized(state: DBInitState) =
        initStateRepo.setInitialized(state)
}