package com.massita.vanhack.ui.jobs.adapter

import android.graphics.Color
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.massita.vanhack.R
import com.massita.vanhack.ui.jobs.JobsFragmentDirections
import com.massita.vanhack.data.Job
import com.massita.vanhack.data.Skills
import kotlinx.android.synthetic.main.job_item.view.*

class JobAdapter : PagedListAdapter<Job, JobAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Job>() {
            override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean =
                    oldItem == newItem

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.job_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val job = getItem(position)
        job?.let {
            viewHolder.bind(it)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(job: Job) {
            itemView.jobTitle.text = job.title
            itemView.jobLocale.text = job.city

            itemView.jobTimestamp.text = DateUtils
                .getRelativeTimeSpanString(
                    job.date.time,
                    System.currentTimeMillis(),
                    DateUtils.MINUTE_IN_MILLIS)

            itemView.skillsChipGroup.removeAllViews()
            addItemsToChipGroup(job.mustHaveSkills, R.color.colorAccent)
            addItemsToChipGroup(job.niceToHaveSkills, R.color.colorPrimaryDark)

            val action = JobsFragmentDirections.actionJobsFragmentToJobDetailFragment()
            action.jobItem = job
            itemView.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_jobsFragment_to_jobDetailFragment, action.arguments)
            )
        }

        fun addItemsToChipGroup(skills: List<Skills>?, colorRes: Int) {
            skills?.let {
                for (skill in it) {
                    val chip = Chip(itemView.context)
                    chip.text = skill.name
                    chip.setTextColor(Color.WHITE)
                    chip.setChipBackgroundColorResource(colorRes)
                    chip.isClickable = false
                    chip.isCheckable = false
                    itemView.skillsChipGroup.addView(chip)
                }
            }
        }
    }
}
