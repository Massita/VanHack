package com.massita.vanhack.feature.jobs

import android.util.Log
import com.massita.vanhack.model.api.JobApi
import com.massita.vanhack.model.data.JobsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class JobsPresenter(var view: JobsContract.View?, var jobApi: JobApi?) : JobsContract.Presenter {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    private var currentItemCount = 0
    private var pagination = 10

    override fun start() {
        view?.prepareRecyclerView()
        loadNextJobs()
    }

    override fun loadNextJobs() {
        jobApi?.let {
            val disposable = it.getJobs(pagination, currentItemCount, null, null, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { onLoadPage(it) },
                    { onLoadError(it) }
                )

            compositeDisposable.add(disposable)
        }
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
        compositeDisposable.dispose()
        jobApi = null
        view = null
    }
}