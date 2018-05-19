package com.testspace;

import android.support.annotation.LayoutRes;

import com.madlab.core.Experiment;

/**
 * @see Experiment
 */
public class CurrentExperiment extends Experiment {
    CurrentExperiment(MainActivity a) {
        super(a);
        a.addTriggers(
                /* add your triggers */
        );
    }

    @Override @LayoutRes
    public int getExperimentLayout() {
        return R.layout.basic_layout;
    }
}
