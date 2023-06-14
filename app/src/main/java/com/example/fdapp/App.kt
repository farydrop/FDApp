package com.example.fdapp

import android.app.Application
import com.example.fdapp.database.OrderDataBase
import com.example.fdapp.database.OrderRepository
import com.example.fdapp.di.viewModelModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val dataBase by lazy { OrderDataBase.getDatabase(this,applicationScope) }
    val repository by lazy { OrderRepository(dataBase.orderDao()) }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@App)
            // Load modules
            modules(viewModelModule)
        }
    }
}