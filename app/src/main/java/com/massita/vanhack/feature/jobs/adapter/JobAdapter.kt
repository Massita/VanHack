package com.massita.vanhack.feature.jobs.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.chip.Chip
import com.massita.vanhack.R
import com.massita.vanhack.model.data.Job
import com.massita.vanhack.model.data.Skills
import kotlinx.android.synthetic.main.job_item.view.*

class JobAdapter(val jobList: MutableList<Job>, val itemClickListener: (Job) -> Unit) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.job_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val job = jobList[position]
        viewHolder.bind(job)
    }

    fun add(jobs: List<Job>) {
        jobList.addAll(jobs)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(job: Job) {
            itemView.jobTitle.text = job.title
            itemView.jobLocale.text = job.city
            // TODO: Fix date
            itemView.jobTimestamp.text = job.date

            itemView.skillsChipGroup.removeAllViews()
            addItemsToChipGroup(job.mustHaveSkills)
            addItemsToChipGroup(job.niceToHaveSkills)
        }

        fun addItemsToChipGroup(skills: List<Skills>) {
            for (skill in skills) {
                val chip = Chip(itemView.context)
                chip.text = skill.name

                chip.isClickable = false
                chip.isCheckable = false
                itemView.skillsChipGroup.addView(chip)
            }
        }
    }
}
