package com.example.fleetmanager.repository.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JobEntity(
   @PrimaryKey val driver_id:String,
    val car_id:String,
    val lat_start:String,
    val long_start:String,
    val lat_end:String,
    val long_end:String,
    val estimate_time: Long
)
