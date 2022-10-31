package com.example.fleetmanager.domain

data class JobData( val driver_id:String,
                    val car_id:String,
                    val lat_start:String,
                    val long_start:String,
                    val lat_end:String,
                    val long_end:String,
                    val estimate_time: Long)
