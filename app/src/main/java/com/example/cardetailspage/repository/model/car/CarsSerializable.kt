package com.example.cardetailspage.repository.model.car

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cardetailspage.entity.car.CarDetails
import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.entity.car.EngineState

@Entity
data class CarDetailsRoom(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "fuel") val fuel: Long,
    @ColumnInfo(name = "door_state") var doorState: DoorState,
    @ColumnInfo(name = "engine_state") val engineState: EngineState,
    @ColumnInfo(name = "date_updated") var dateUpdated: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "image") val image: String = ""
)

fun CarDetailsRoom.toCarDetails() = apply {
    CarDetails(
        id = id,
        name = name,
        fuel = fuel,
        doorState = doorState,
        engineState = engineState,
        dateUpdated = dateUpdated,
        image = image
    )
}

@Entity
data class DBInitState(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    @ColumnInfo(name = "is_initialized", defaultValue = "false") val isInitialized: Boolean = false
)