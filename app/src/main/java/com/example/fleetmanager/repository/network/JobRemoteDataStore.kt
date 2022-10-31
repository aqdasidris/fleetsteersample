package com.example.fleetmanager.repository.network

class JobRemoteDataStore constructor(val jobAPi: JobRemoteAPI): IJobRemoteDataStore {
    override suspend fun getAll(): List<JobNetworkModel> {
        return jobAPi.driverdetails()?.let {
            listOf(it)
        } ?:  emptyList<JobNetworkModel>()
    }
}