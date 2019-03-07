package com.massita.vanhack.feature.jobs

import com.massita.vanhack.model.data.Skills

interface JobDetailContract {

    interface View {

        fun setJobTitle(title: String?)

        fun setJobLocation(location: String?)

        fun setJobType(type: String?)

        fun setJobSalary(salary: String?)

        fun setSalaryNotInformed()

        fun setJobDescription(description: String?)

        fun setMustHaveSkills(skills: List<Skills>)

        fun hideMustHaveSkills()

        fun setNiceToHaveSkills(skills: List<Skills>)

        fun hideNiceToHaveSkills()

    }

    interface Presenter {

        //TODO: apply button

        fun start()

    }

}