package com.example.fleetmanager.repository

import com.example.fleetmanager.domain.JobData

interface IJobRepository {
    suspend fun getAllJobs(): List<JobData>
}