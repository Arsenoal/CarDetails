package com.example.cardetailspage.di

import android.content.Context
import androidx.room.Room
import com.example.cardetailspage.repository.service.AppDb
import com.example.cardetailspage.repository.service.DB_NAME
import org.koin.dsl.module

fun dbModule(context: Context) = module {
    single {
        Room.databaseBuilder(context, AppDb::class.java, DB_NAME).build()
    }

    single {
        get<AppDb>().carsDao()
    }

    single {
        get<AppDb>().dbInitDao()
    }
}