package com.example.fleetmanager.repository.database

class LocalDataStore constructor(val jobDao: JobDao): IJobLocalDataStore {
    override suspend fun get(): List<JobEntity> {
        return jobDao.getAll()
    }

    override suspend fun save(items: List<JobEntity>) {
        jobDao.save(items)
    }

    override suspend fun clear(items: List<JobEntity>) {
        jobDao.clear(items)
    }
}