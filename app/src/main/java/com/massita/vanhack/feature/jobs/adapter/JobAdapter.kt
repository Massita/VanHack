package com.massita.vanhack.feature.jobs.adapter

import android.graphics.Color
import android.text.format.DateUtils
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

            itemView.jobTimestamp.text = DateUtils
                .getRelativeTimeSpanString(
                    job.date.time,
                    System.currentTimeMillis(),
                    DateUtils.MINUTE_IN_MILLIS)

            itemView.skillsChipGroup.removeAllViews()
            addItemsToChipGroup(job.mustHaveSkills, R.color.colorAccent)
            addItemsToChipGroup(job.niceToHaveSkills, R.color.colorPrimaryDark)
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
