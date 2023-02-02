package com.example.cardetailspage.repository.service.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.cardetailspage.entity.car.DoorState
import com.example.cardetailspage.repository.model.car.CarDetailsRoom

@Dao
interface CarDetailsDao {

    @Query("SELECT * FROM cardetailsroom")
    suspend fun getAll(): List<CarDetailsRoom>

    @Query("SELECT id FROM cardetailsroom")
    suspend fun getCarIds(): List<String>

    @Query("SELECT name FROM cardetailsroom WHERE id = :carId")
    suspend fun getCarName(carId: String): String

    @Query("SELECT image FROM cardetailsroom WHERE id = :carId")
    suspend fun getCarImage(carId: String): String

    @Query("UPDATE cardetailsroom SET date_updated = :lastUpdateTime WHERE id = :carId")
    suspend fun updateCarById(carId: String, lastUpdateTime: Long)

    @Query("UPDATE cardetailsroom SET door_state = :newState WHERE id = :carId")
    suspend fun updateDoorState(carId: String, newState: DoorState)

    @Query("SELECT door_state FROM cardetailsroom WHERE id = :carId")
    suspend fun getCarDoorState(carId: String): DoorState

    @Query("SELECT fuel FROM cardetailsroom WHERE id = :carId")
    suspend fun getCarFuel(carId: String): Long

    @Query("SELECT date_updated FROM cardetailsroom WHERE id = :carId")
    suspend fun getDateUpdated(carId: String): Long

    @Insert
    suspend fun insertAll(carDetails: List<CarDetailsRoom>)

    @Delete
    suspend fun delete(carDetailsRoom: CarDetailsRoom)

}