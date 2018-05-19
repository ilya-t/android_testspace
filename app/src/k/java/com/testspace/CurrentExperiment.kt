package com.testspace

import android.support.annotation.LayoutRes
import com.madlab.MadLabActivity
import com.madlab.core.Experiment

/**
 * @see Experiment
 */
class CurrentExperiment : Experiment {
    @LayoutRes
    override fun getExperimentLayout() = R.layout.basic_layout

    override fun onSetupExperiment(a: MadLabActivity) {
        a.addTriggers(
                /* add your triggers */
        )
    }
}