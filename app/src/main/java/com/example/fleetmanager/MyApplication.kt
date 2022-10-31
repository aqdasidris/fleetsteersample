package com.example.fleetmanager

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fleetmanager.repository.database.DatabaseProvider
import com.example.fleetmanager.repository.database.JobEntity
import com.example.fleetmanager.repository.database.JobEntityDatabase

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DatabaseProvider.initialize(this)
    }

}