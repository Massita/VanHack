package com.massita.vanhack.ui.jobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.massita.vanhack.R
import com.massita.vanhack.presentation.api.JobApi
import com.massita.vanhack.presentation.data.Job
import com.massita.vanhack.presentation.data.JobsViewModel
import com.massita.vanhack.ui.jobs.adapter.JobAdapter
import kotlinx.android.synthetic.main.fragment_jobs.*


class JobsFragment : Fragment(), JobsContract.View {
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var viewAdapter: JobAdapter

    private lateinit var jobsViewModel: JobsViewModel

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

        jobsViewModel = ViewModelProviders.of(this)
            .get(JobsViewModel::class.java)

        mPresenter = JobsPresenter(this, JobApi.create())
        mPresenter.start()
    }

    override fun onDestroyView() {
        mPresenter.destroy()
        super.onDestroyView()
    }

    override fun prepareRecyclerView() {
        viewManager = LinearLayoutManager(context)
        viewAdapter = JobAdapter()


        val dividerItemDecoration = DividerItemDecoration(context, viewManager.orientation)
        jobsRecyclerView.apply {
            addItemDecoration(dividerItemDecoration)
            layoutManager = viewManager
            setHasFixedSize(true)
            adapter = viewAdapter
        }

        jobsViewModel.jobsList.observe(this, Observer { viewAdapter.submitList(it) })

    }

    override fun addJobs(jobs: List<Job>) {
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
}
