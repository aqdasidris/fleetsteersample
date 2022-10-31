package com.example.fleetmanager.repository.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object DatabaseProvider {
    private var db: JobEntityDatabase ? = null
    fun initialize(applicationContext: Context){
        db = Room.databaseBuilder(
            applicationContext,
            JobEntityDatabase::class.java,"Job Database"
        ).build()

    }
    fun getInstance(): JobEntityDatabase?{
        return db
    }
}