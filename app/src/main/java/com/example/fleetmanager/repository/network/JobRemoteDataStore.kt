package com.example.fleetmanager.repository.network

import javax.inject.Inject

class JobRemoteDataStore  @Inject constructor(val jobAPi: JobRemoteAPI): IJobRemoteDataStore {
    override suspend fun getAll(): List<JobNetworkModel> {
        return jobAPi.driverdetails()?.let {
            listOf(it)
        } ?:  emptyList<JobNetworkModel>()
    }
}