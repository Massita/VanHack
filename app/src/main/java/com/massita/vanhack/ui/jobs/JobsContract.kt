package com.massita.vanhack.ui.jobs

import com.massita.vanhack.presentation.data.Job

interface JobsContract {

    interface View {

        fun prepareRecyclerView()

        fun addJobs(jobs: List<Job>)

        fun showLoading()

        fun hideLoading()

        fun showErrorMessage()

    }

    interface Presenter {

        fun start()

        fun loadNextJobs()

        fun destroy()

    }

}