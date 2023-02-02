package com.example.cardetailspage.repository.service

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cardetailspage.repository.model.car.CarDetailsRoom
import com.example.cardetailspage.repository.model.car.DBInitState
import com.example.cardetailspage.repository.service.room.CarDetailsDao
import com.example.cardetailspage.repository.service.room.DbInitDao

const val DB_NAME = "APP_DB"

@Database(entities = [CarDetailsRoom::class, DBInitState::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDb: RoomDatabase() {
    abstract fun carsDao(): CarDetailsDao

    abstract fun dbInitDao(): DbInitDao
}