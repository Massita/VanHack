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
import com.massita.vanhack.viewmodel.JobsViewModel
import com.massita.vanhack.ui.jobs.adapter.JobAdapter
import kotlinx.android.synthetic.main.fragment_jobs.*


class JobsFragment : Fragment() {
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var viewAdapter: JobAdapter

    private lateinit var jobsViewModel: JobsViewModel


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
        prepareRecyclerView()
    }

    fun prepareRecyclerView() {
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

    fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun showErrorMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
