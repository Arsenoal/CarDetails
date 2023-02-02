package com.example.cardetailspage.entity.car

import java.io.Serializable

data class CarDetails(
    val id: String,
    val name: String,
    val fuel: Long,
    var doorState: DoorState,
    val engineState: EngineState,
    var dateUpdated: Long = System.currentTimeMillis(),
    val image: String = ""
) : Serializable {
    companion object
}

sealed class DoorState: Serializable {
    object Locked: DoorState()

    object Unlocked: DoorState()

    object Processing: DoorState()

    object NotDefined: DoorState()
}

sealed class EngineState: Serializable {
    object Started: EngineState()

    object Stopped: EngineState()

    object Processing: EngineState()

    object NotDefined: EngineState()
}

fun CarDetails.Companion.empty() = CarDetails(
    id = "",
    name = "No car to present",
    fuel = 0L,
    doorState = DoorState.NotDefined,
    engineState = EngineState.NotDefined,
    image = ""
)

fun CarDetails.copyWithDateLoss() = CarDetails(
    id = this.id,
    name = this.name,
    fuel = this.fuel,
    doorState = this.doorState,
    engineState = this.engineState,
    dateUpdated = System.currentTimeMillis(),
    image = this.image
)

fun CarDetails.getLastUpdateTime(lastStartDate: Long = System.currentTimeMillis()): String {
    val diff: Long = lastStartDate - dateUpdated
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24

    return if(days == 0L) {
        if(hours == 0L) {
            if(minutes == 0L) {
                "$seconds sec"
            } else {
                "$minutes min"
            }
        } else {
            "$hours hours"
        }
    } else {
        "$days days"
    }
}