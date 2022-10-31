package com.example.fleetmanager.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JobEntity::class], version = 1)
abstract class JobEntityDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao
}