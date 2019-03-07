package com.massita.vanhack.data

import androidx.paging.ItemKeyedDataSource
import com.massita.vanhack.api.JobApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobsDataSource : ItemKeyedDataSource<Int, Job>() {

    private val mJobApi: JobApi by lazy { JobApi.create() }

    private var itemCount = 0

    private var mQuery: String? = null

    fun setQuery(query: String) {
        mQuery = query
    }


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Job>) {
        // TODO: Remove the nulls
        mJobApi.getJobs(params.requestedLoadSize, 0, mQuery, null, null, null)
            .enqueue(object : Callback<JobsResponse> {
                override fun onFailure(call: Call<JobsResponse>, t: Throwable) {
                    // TODO: Do nothing at this moment
                }

                override fun onResponse(call: Call<JobsResponse>, response: Response<JobsResponse>) {
                    itemCount += response.body()?.searchResult?.totalCount!!
                    callback.onResult(response.body()?.searchResult?.jobs!!)
                }

            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Job>) {
        mJobApi.getJobs(params.requestedLoadSize, params.key, mQuery, null, null, null)
            .enqueue(object : Callback<JobsResponse> {
                override fun onFailure(call: Call<JobsResponse>, t: Throwable) {
                    // TODO: Do nothing at this moment
                }

                override fun onResponse(call: Call<JobsResponse>, response: Response<JobsResponse>) {
                    itemCount += response.body()?.searchResult?.totalCount!!
                    callback.onResult(response.body()?.searchResult?.jobs!!)
                }

            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Job>) {

    }

    override fun getKey(item: Job): Int {
        return itemCount
    }

}