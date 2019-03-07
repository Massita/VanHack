package com.massita.vanhack.presentation.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class JobsViewModel : ViewModel() {

    var jobsList: LiveData<PagedList<Job>>

    private val pageSize = 10
    private val jobsDataSourceFactory: JobsDataFactory = JobsDataFactory()

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        jobsList = LivePagedListBuilder<Int, Job>(jobsDataSourceFactory, config).build()
    }

}