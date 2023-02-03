package com.example.cardetailspage.repository.model.car

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cardetailspage.entity.car.CarDetails
import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.entity.car.EngineState
import com.example.cardetailspage.repository.model.Mock

@Entity
data class CarDetailsRoom(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "fuel") val fuel: Long,
    @ColumnInfo(name = "door_state") var doorState: DoorState,
    @ColumnInfo(name = "engine_state") val engineState: EngineState,
    @ColumnInfo(name = "date_updated") var dateUpdated: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "image") val image: String = ""
) {
    companion object
}

fun CarDetailsRoom.toCarDetails() =
    CarDetails(
        id = id,
        name = name,
        fuel = fuel,
        doorState = doorState,
        engineState = engineState,
        dateUpdated = dateUpdated,
        image = image
    )

fun CarDetailsRoom.Companion.mocked(carId: String) =
    CarDetailsRoom(
        id = carId,
        name = Mock.CAR_NAME[carId]!!,
        fuel = Mock.CAR_FUEL[carId]!!,
        doorState = DoorState.Locked,
        engineState = EngineState.Stopped,
        image = Mock.CAR_IMAGE[carId]!!
    )

@Entity
data class DBInitState(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    @ColumnInfo(name = "is_initialized", defaultValue = "false") val isInitialized: Boolean = false
)