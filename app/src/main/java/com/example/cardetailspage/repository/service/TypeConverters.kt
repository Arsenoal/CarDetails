package com.example.cardetailspage.repository.service

import androidx.room.TypeConverter
import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.entity.car.EngineState
import com.example.cardetailspage.repository.common.asString
import com.example.cardetailspage.repository.common.toDoorState
import com.example.cardetailspage.repository.common.toEngineState

class Converters {
    @TypeConverter
    fun doorStateToString(doorState: DoorState): String = doorState.asString()

    @TypeConverter
    fun stringToDoorState(doorStateString: String): DoorState = doorStateString.toDoorState()

    @TypeConverter
    fun engineStateToString(engineState: EngineState): String = engineState.asString()

    @TypeConverter
    fun stringToEngineState(engineStateString: String): EngineState = engineStateString.toEngineState()
}