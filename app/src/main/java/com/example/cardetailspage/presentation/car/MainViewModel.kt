package com.example.cardetailspage.presentation.car

import androidx.lifecycle.MutableLiveData
import com.example.cardetailspage.base.BaseViewModel
import com.example.cardetailspage.domain.car.CarIdsUseCase
import com.example.cardetailspage.domain.car.cardetails.CarDetailsUseCase
import com.example.cardetailspage.domain.car.init.InitCarsUseCase
import com.example.cardetailspage.domain.car.init.InitStateUseCase
import com.example.cardetailspage.entity.car.CarDetails
import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.repository.model.Mock
import com.example.cardetailspage.repository.model.car.CarDetailsRoom
import com.example.cardetailspage.repository.model.car.DBInitState
import com.example.cardetailspage.repository.model.car.mocked
import kotlinx.coroutines.delay

class MainViewModel(
    private val carIds: CarIdsUseCase,
    private val carDetailsUseCase: CarDetailsUseCase,
    private val initStateUseCase: InitStateUseCase,
    private val initCarsUseCase: InitCarsUseCase
): BaseViewModel() {

    val carDetailsLiveData by lazy { MutableLiveData<List<CarDetails>>() }

    init { initDb() }

    fun getCarDetails() = launchOnDefault {
        delay(500)
        val carDetails = carIds.getIds().map { carId -> carDetailsUseCase.getCarDetails(carId) }

        switchToUi { carDetailsLiveData.value = carDetails }
    }

    fun setUpdateDate(carId: String) = launchOnDefault { carDetailsUseCase.resetUpdateDate(carId) }

    fun changeDoorState(carId: String, doorState: DoorState) = launchOnDefault { carDetailsUseCase.changeDoorState(carId, doorState) }

    private fun initDb() = launchOnDefault {
        val isInitialized = try { initStateUseCase.isInitialized() }
        catch (ex: Exception) { false }

        if(!isInitialized) {
            initStateUseCase.setInitialized(DBInitState(isInitialized = true))
            initCarsUseCase.addAll(listOf(CarDetailsRoom.mocked(Mock.CAR_IDS[0])))
        }
    }

}