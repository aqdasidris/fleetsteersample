package com.example.fleetmanager.di

import com.example.fleetmanager.repository.IJobRepository
import com.example.fleetmanager.repository.JobRepository
import com.example.fleetmanager.repository.database.IJobLocalDataStore
import com.example.fleetmanager.repository.database.JobDao
import com.example.fleetmanager.repository.database.LocalDataStore
import com.example.fleetmanager.repository.network.IJobRemoteDataStore
import com.example.fleetmanager.repository.network.JobRemoteAPI
import com.example.fleetmanager.repository.network.JobRemoteDataStore
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideRemoteDataStore(jobRemoteAPI: JobRemoteAPI): IJobRemoteDataStore{
        return JobRemoteDataStore(jobRemoteAPI)
    }
    //todo provide JobDao

    @Provides
    fun providesLocalDataStore(jobDao: JobDao): IJobLocalDataStore{
        return LocalDataStore(jobDao)
    }

    @Provides
    fun providesJobRepository(localDataStore: IJobLocalDataStore, remoteDataStore: IJobRemoteDataStore): IJobRepository{
        return JobRepository(localProvider = localDataStore, remoteProvider = remoteDataStore)
    }
}