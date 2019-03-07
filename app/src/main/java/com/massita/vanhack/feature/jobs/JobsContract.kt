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

    }

    interface Presenter {

        fun start()

        fun loadNextJobs()

        fun destroy()

    }

}