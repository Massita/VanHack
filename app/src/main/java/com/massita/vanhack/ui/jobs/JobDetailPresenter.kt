package com.massita.vanhack.ui.jobs

import com.massita.vanhack.data.Job

class JobDetailPresenter(var mView: JobDetailContract.View, var mJob: Job?) : JobDetailContract.Presenter {

    override fun start() {
        mJob?.let {
            mView.setJobTitle(it.title)
            mView.setJobDescription(it.description)
            mView.setJobLocation(it.city + ", " + it.country)
            mView.setJobType(it.jobType)

            if (it.salaryRangeStart != null && it.salaryRangeEnd != null) {
                mView.setJobSalary(it.salaryRangeStart + " - " + it.salaryRangeEnd)
            } else {
                mView.setSalaryNotInformed()
            }

            if(it.mustHaveSkills != null && it.mustHaveSkills!!.isNotEmpty()) {
                mView.setMustHaveSkills(it.mustHaveSkills!!)
            } else {
                mView.hideMustHaveSkills()
            }

            if(it.niceToHaveSkills != null && it.niceToHaveSkills!!.isNotEmpty()) {
                mView.setNiceToHaveSkills(it.niceToHaveSkills!!)
            } else {
                mView.hideNiceToHaveSkills()
            }
        } ?: run {
            // TODO: error
        }
    }

}