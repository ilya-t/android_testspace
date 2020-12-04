package com.testspace

import androidx.annotation.LayoutRes
import com.testspace.core.Experiment
import com.testspace.core.ExperimentActivity

/**
 * @see Experiment
 */
class CurrentExperiment(a: ExperimentActivity) : Experiment(a) {
    init {
        a.addTriggers(
                /* add triggers here */
        )
    }

    @LayoutRes
    override fun getExperimentLayout() = R.layout.basic_layout
}