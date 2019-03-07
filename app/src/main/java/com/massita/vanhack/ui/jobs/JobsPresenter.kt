package com.massita.vanhack.ui.jobs

import android.util.Log
import com.massita.vanhack.presentation.api.JobApi
import com.massita.vanhack.presentation.data.JobsResponse

class JobsPresenter(var view: JobsContract.View?, var jobApi: JobApi?) : JobsContract.Presenter {

    private var currentItemCount = 0

    override fun start() {
        view?.prepareRecyclerView()
        loadNextJobs()
    }

    override fun loadNextJobs() {

    }

    private fun onLoadError(throwable: Throwable?) {
        Log.d("API", throwable.toString())
        // TODO: Show Error
    }

    private fun onLoadPage(jobsResponse: JobsResponse?) {
        jobsResponse?.let {
            view?.addJobs(it.searchResult.jobs)
            currentItemCount += it.searchResult.totalCount
        }
    }

    override fun destroy() {
        jobApi = null
        view = null
    }
}