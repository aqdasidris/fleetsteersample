package com.example.fleetmanager.repository.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JobDao {
    @Query("Select * from JobEntity")
    suspend fun getAll(): List<JobEntity>

    @Insert
    suspend fun save(items: List<JobEntity>)

    @Delete
    suspend fun clear(items: List<JobEntity>)
}