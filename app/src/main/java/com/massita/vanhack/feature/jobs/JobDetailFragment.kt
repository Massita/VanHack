package com.massita.vanhack.feature.jobs

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.massita.vanhack.R
import com.massita.vanhack.model.data.Job
import com.massita.vanhack.model.data.Skills
import kotlinx.android.synthetic.main.fragment_job_detail.*

class JobDetailFragment : Fragment(), JobDetailContract.View {

    companion object {
        const val ARGUMENT_JOB = "ARGUMENT_JOB"

        fun newInstance(job: Job) : Fragment {
            val fragment = JobDetailFragment()

            val bundle = Bundle()
            bundle.putSerializable(ARGUMENT_JOB, job)
            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var mPresenter: JobDetailContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_job_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = JobDetailPresenter(this, arguments?.getSerializable(ARGUMENT_JOB) as Job?)
        mPresenter.start()
    }

    // Contract
    override fun setJobTitle(title: String) {
        jobTitle.text = title
    }

    override fun setJobLocation(location: String) {
        jobLocale.text = location
    }

    override fun setJobType(type: String) {
        jobType.text = type
    }

    override fun setJobSalary(salary: String) {
        salaryRange.text = salary
    }

    override fun setSalaryNotInformed() {
        salaryRange.text = getString(R.string.salary_not_informed)
    }

    override fun setJobDescription(description: String) {
        jobDescription.text = description
    }

    override fun setMustHaveSkills(skills: List<Skills>) {
        addItemsToChipGroup(skills, mustHaveSkills)
    }

    override fun setNiceToHaveSkills(skills: List<Skills>) {
        addItemsToChipGroup(skills, niceToHaveSkills)
    }

    override fun hideMustHaveSkills() {
        mustHaveSkills.visibility = View.GONE
        must_have_skills_title.visibility = View.GONE
    }

    override fun hideNiceToHaveSkills() {
        niceToHaveSkills.visibility = View.GONE
        nice_to_have_skills_title.visibility = View.GONE
    }

    private fun addItemsToChipGroup(skills: List<Skills>, chipGroup: ChipGroup) {
        for (skill in skills) {
            val chip = Chip(context)
            chip.text = skill.name
            chip.setTextColor(Color.WHITE)
            chip.setChipBackgroundColorResource(R.color.colorPrimaryDark)
            chip.isClickable = false
            chip.isCheckable = false
            chipGroup.addView(chip)
        }
    }

}