package com.example.fleetmanager.repository.mappers

import com.example.fleetmanager.repository.database.JobEntity
import com.example.fleetmanager.repository.network.JobNetworkModel


fun JobNetworkModel.toDatabaseModel(): JobEntity{
   return JobEntity(
       driver_id = this.driver_id,
       car_id = this.car_id,
       lat_start=this.lat_start,
       long_start=this.long_start,
       lat_end=this.lat_end,
       long_end=this.long_end,
       estimate_time=this.estimate_time
       )
}



fun Int.spell(): String{
    return if(this ==1) "One" else ""
}