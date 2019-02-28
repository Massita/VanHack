package com.massita.vanhack.feature.jobs

import com.massita.vanhack.model.data.Job

interface JobsContract {

    interface View {

        fun prepareRecyclerView()

        fun addJobs(jobs: List<Job>)

        fun showLoading()

        fun hideLoading()

        fun showErrorMessage()

        fun resetScrollListenerStatus()

        fun openJobDetails(job: Job)

    }

    interface Presenter {

        fun start()

        fun loadNextJobs()

        fun destroy()

        fun onItemClick(job: Job)

    }

}