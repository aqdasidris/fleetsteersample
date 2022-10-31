package com.example.fleetmanager.repository.database

interface IJobLocalDataStore {
    suspend fun get(): List<JobEntity>
    suspend fun save(items: List<JobEntity>)
    suspend fun clear(items: List<JobEntity>)
}