package com.example.cardetailspage.repository.common

import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.entity.car.EngineState

fun String.toDoorState() = when(this) {
    "Locked" -> DoorState.Locked
    "Unlocked" -> DoorState.Unlocked
    else -> DoorState.Processing
}

fun DoorState.asString() = when(this) {
    is DoorState.Locked -> "Locked"
    is DoorState.Unlocked -> "Unlocked"
    else -> "..."
}

fun String.toEngineState() = when(this) {
    "Start" -> EngineState.Started
    "Stop" -> EngineState.Stopped
    else -> EngineState.Processing
}

fun EngineState.asString() = when(this) {
    is EngineState.Started -> "Start"
    is EngineState.Stopped -> "Stop"
    else -> "..."
}