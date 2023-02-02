package com.example.cardetailspage.car.presentation.main

import androidx.lifecycle.MutableLiveData
import com.example.cardetailspage.base.BaseViewModel
import com.example.cardetailspage.domain.car.CarIdsUseCase
import com.example.cardetailspage.domain.car.cardetails.CarDetailsUseCase
import com.example.cardetailspage.domain.car.cardetails.CarDoorsUseCase
import com.example.cardetailspage.domain.car.cardetails.CarFuelUseCase
import com.example.cardetailspage.domain.car.init.InitCarsUseCase
import com.example.cardetailspage.domain.car.init.InitStateUseCase
import com.example.cardetailspage.entity.car.CarDetails
import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.entity.car.EngineState
import com.example.cardetailspage.repository.model.Mock
import com.example.cardetailspage.repository.model.car.CarDetailsRoom
import com.example.cardetailspage.repository.model.car.DBInitState
import kotlinx.coroutines.delay

class MainViewModel(
    private val carIds: CarIdsUseCase,
    private val carFuel: CarFuelUseCase,
    private val carDoors: CarDoorsUseCase,
    private val carDetailsUseCase: CarDetailsUseCase,
    private val initStateUseCase: InitStateUseCase,
    private val initCarsUseCase: InitCarsUseCase
): BaseViewModel() {

    val carDetailsLiveData by lazy {
        MutableLiveData<List<CarDetails>>()
    }

    init {
        initDb()
    }

    fun getCarDetails() = launchOnDefault {
        delay(500)
        val carDetails = carIds.getIds().map { carId ->
            CarDetails(
                id = carId,
                name = carDetailsUseCase.getCarName(carId),
                fuel = carFuel.getFuelLeft(carId),
                doorState = carDoors.getCarDoorsState(carId),
                dateUpdated = carDetailsUseCase.getDateUpdated(carId),
                engineState = EngineState.NotDefined,
                image = carDetailsUseCase.getCarImage(carId)
            )
        }

        println("cars: ${carDetails.size}")

        switchToUi { carDetailsLiveData.value = carDetails }
    }

    fun setUpdateDate(carId: String) = launchOnDefault {
        carDetailsUseCase.resetUpdateDate(carId)
    }

    fun changeDoorState(carId: String, doorState: DoorState) = launchOnDefault {
        carDoors.changeState(carId, doorState)
    }

    private fun initDb() = launchOnDefault {
        val isInitialized = try {
            initStateUseCase.isInitialized()
        } catch (ex: Exception) {
            false
        }
        if(!isInitialized) {
            initStateUseCase.setInitialized(DBInitState(isInitialized = true))
            val carId = Mock.CAR_IDS[0]
            initCarsUseCase.addAll(listOf(CarDetailsRoom(
                id = carId,
                name = Mock.CAR_NAME[carId]!!,
                fuel = Mock.CAR_FUEL[carId]!!,
                doorState = DoorState.Locked,
                engineState = EngineState.Stopped,
                image = Mock.CAR_IMAGE[carId]!!
            )))
        }
    }

}