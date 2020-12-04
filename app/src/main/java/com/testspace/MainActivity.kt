package com.testspace

import com.testspace.core.ExperimentActivity

class MainActivity : ExperimentActivity() {
    override fun createExperiment() = CurrentExperiment(this)
}
