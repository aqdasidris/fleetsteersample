package com.example.fleetmanager.repository.network

interface IJobRemoteDataStore {
    suspend fun getAll(): List<JobNetworkModel>
}