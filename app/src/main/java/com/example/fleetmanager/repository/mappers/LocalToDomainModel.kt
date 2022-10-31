package com.example.fleetmanager.repository.mappers

import com.example.fleetmanager.domain.JobData
import com.example.fleetmanager.repository.database.JobEntity

fun JobEntity.toDomainModel(): JobData {
    return JobData(
        driver_id = this.driver_id,
        car_id = this.car_id,
        lat_start = this.lat_start,
        long_start = this.long_start,
        lat_end = this.lat_end,
        long_end = this.long_end,
        estimate_time = this.estimate_time
    )
}