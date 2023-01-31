package com.example.cardetailspage.car.presentation.main

import androidx.lifecycle.MutableLiveData
import com.example.cardetailspage.base.BaseViewModel
import com.example.cardetailspage.domain.car.CarIdsUseCase
import com.example.cardetailspage.domain.car.cardetails.CarDetailsUseCase
import com.example.cardetailspage.domain.car.cardetails.CarDoorsUseCase
import com.example.cardetailspage.domain.car.cardetails.CarFuelUseCase
import com.example.cardetailspage.entity.car.CarDetails
import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.entity.car.EngineState

class MainViewModel(
    private val carIds: CarIdsUseCase,
    private val carFuel: CarFuelUseCase,
    private val carDoors: CarDoorsUseCase,
    private val carDetailsUseCase: CarDetailsUseCase
): BaseViewModel() {

    val carDetailsLiveData by lazy {
        MutableLiveData<List<CarDetails>>()
    }

    fun getCarDetails() = launchOnDefault {
        val carDetails = carIds.getIds().map { carId ->
            CarDetails(
                id = carId,
                name = carDetailsUseCase.getCarName(carId),
                fuel = carFuel.getFuelLeft(carId),
                doorState = carDoors.getCarDoorsState(carId),
                engineState = EngineState.NotDefined,
                image = carDetailsUseCase.getCarImage(carId)
            )
        }

        switchToUi { carDetailsLiveData.value = carDetails }
    }

    fun changeDoorState(carId: String, doorState: DoorState) = launchOnDefault {
        carDoors.changeState(carId, doorState)
    }

}