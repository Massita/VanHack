package com.massita.vanhack.presentation.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource


class JobsDataFactory : DataSource.Factory<Int, Job>() {

    val jobsDataSourceLiveData = MutableLiveData<JobsDataSource>()

    override fun create(): DataSource<Int, Job> {
        val jobsDataSource = JobsDataSource()
        jobsDataSourceLiveData.postValue(jobsDataSource)
        return jobsDataSource
    }

}