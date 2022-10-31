package com.example.fleetmanager.repository

import com.example.fleetmanager.domain.JobData
import com.example.fleetmanager.repository.database.IJobLocalDataStore
import com.example.fleetmanager.repository.database.JobDao
import com.example.fleetmanager.repository.database.JobEntity

import com.example.fleetmanager.repository.network.IJobRemoteDataStore
import com.example.fleetmanager.repository.mappers.toDatabaseModel
import com.example.fleetmanager.repository.mappers.toDomainModel
import com.example.fleetmanager.repository.network.JobNetworkModel
import io.ktor.http.*

class JobRepository(private val localProvider: IJobLocalDataStore, private val remoteProvider: IJobRemoteDataStore): IJobRepository {

    override suspend fun getAllJobs(): List<JobData> {
        clear()
        fetch()
        val queryResult = query()
        val domainJobs = queryResult.map {
            it.toDomainModel()
        }
        return domainJobs
    }

    private suspend fun fetch(){
        val networkJobs = remoteProvider.getAll()
        save(networkJobs)
    }

    private suspend fun save(jobs: List<JobNetworkModel>){
        val localJobs  = jobs.map {
            it.toDatabaseModel()
        }
        localProvider.save(localJobs)
    }


    private suspend fun query(): List<JobEntity>{
        return localProvider.get()
    }

    private suspend fun clear(){
        val jobs=localProvider.get()
        localProvider.clear(jobs)
    }
}