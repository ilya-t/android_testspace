package com.testspace

import android.support.annotation.LayoutRes
import com.madlab.MadLabActivity
import com.madlab.core.Experiment

/**
 * @see Experiment
 */
class CurrentExperiment(a: MadLabActivity) : Experiment(a) {
    init {
        a.addTriggers(
                /* add triggers here */
        )
    }
    @LayoutRes
    override fun getExperimentLayout() = R.layout.basic_layout
}