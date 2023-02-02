package com.example.cardetailspage

import android.app.Application
import com.example.cardetailspage.di.DomainModule
import com.example.cardetailspage.di.PresentationModule
import com.example.cardetailspage.di.RepoModule
import com.example.cardetailspage.di.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class CarDetailsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            loadKoinModules(
                listOf(
                    dbModule(applicationContext),
                    RepoModule,
                    DomainModule,
                    PresentationModule)
            )
        }
    }
}