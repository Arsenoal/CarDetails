package com.example.cardetailspage.car.repository.common

import com.example.cardetailspage.entity.car.DoorState

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