package com.example.cardetailspage.car.repository.car.carDetails

import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.car.repository.common.asString
import com.example.cardetailspage.car.repository.common.toDoorState
import com.example.cardetailspage.car.repository.model.Mock
import kotlinx.coroutines.delay

class SimplyCarDoorsRepo: CarDoorsRepo {

    override suspend fun getCarDoorsState(id: String) = Mock.CAR_DOOR_STATE[id]?.toDoorState() ?: DoorState.Locked

    override suspend fun changeState(id: String, newState: DoorState) {
        Mock.CAR_DOOR_STATE[id] = DoorState.Processing.asString()
        delay(5000)
        Mock.CAR_DOOR_STATE[id] = newState.asString()
    }
}