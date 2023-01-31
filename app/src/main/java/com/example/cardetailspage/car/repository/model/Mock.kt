package com.example.cardetailspage.car.repository.model

object Mock {
    private const val CAR_ID = "4e7933c0-a082-11ed-a8fc-0242ac120002"

    val CAR_IDS = listOf(CAR_ID)

    val CAR_NAME = mapOf(CAR_ID to "My QX55")

    val CAR_FUEL = mutableMapOf(CAR_ID to 120L)

    val CAR_DOOR_STATE = mutableMapOf(CAR_ID to "Locked")

    val CAR_IMAGE = mutableMapOf(CAR_ID to "https://www.petrovskiy.ru/upload/iblock/862/prev.jpg")
}