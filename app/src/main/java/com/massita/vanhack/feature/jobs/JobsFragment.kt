package com.massita.vanhack.feature.jobs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.massita.vanhack.R
import com.massita.vanhack.feature.jobs.adapter.JobAdapter
import com.massita.vanhack.model.api.JobApi
import com.massita.vanhack.model.data.Job
import com.massita.vanhack.utils.EndlessScrollListener
import kotlinx.android.synthetic.main.fragment_jobs.*


class JobsFragment : Fragment(), JobsContract.View {
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var viewAdapter: JobAdapter
    private lateinit var endlessScrollListener: EndlessScrollListener

    private lateinit var mPresenter : JobsContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        return inflater.inflate(R.layout.fragment_jobs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = JobsPresenter(this, JobApi.create())
        mPresenter.start()
    }

    override fun onDestroyView() {
        mPresenter.destroy()
        super.onDestroyView()
    }

    override fun prepareRecyclerView() {
        viewManager = LinearLayoutManager(context)
        viewAdapter = JobAdapter(ArrayList(), { clickedItem -> mPresenter.onItemClick(clickedItem) })
        endlessScrollListener = object: EndlessScrollListener(viewManager) {
            override fun onLoadMore() {
                mPresenter.loadNextJobs()
            }
        }

        val dividerItemDecoration = DividerItemDecoration(context, viewManager.orientation)
        jobsRecyclerView.apply {
            addItemDecoration(dividerItemDecoration)
            layoutManager = viewManager
            setHasFixedSize(true)
            adapter = viewAdapter
            addOnScrollListener(endlessScrollListener)
        }

    }

    override fun addJobs(jobs: List<Job>) {
        viewAdapter.add(jobs)
        viewAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resetScrollListenerStatus() {
        endlessScrollListener.resetStatus()
    }

    override fun openJobDetails(job: Job) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
