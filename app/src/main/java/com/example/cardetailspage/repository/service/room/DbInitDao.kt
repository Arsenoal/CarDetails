package com.example.cardetailspage.repository.service.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cardetailspage.repository.model.car.DBInitState

@Dao
interface DbInitDao {
    @Query("SELECT is_initialized FROM dbinitstate")
    suspend fun isInitialized(): Boolean

    @Insert
    suspend fun setInitialized(state: DBInitState)
}