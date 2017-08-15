package com.testspace;

import android.support.annotation.LayoutRes;

import com.madlab.MadLabActivity;
import com.madlab.core.Experiment;

/**
 * @see Experiment
 */
public class CurrentExperiment implements Experiment {
    @Override @LayoutRes
    public int getExperimentLayout() {
        return R.layout.basic_layout;
    }

    @Override
    public void onSetupExperiment(MadLabActivity a) {
        a.addTriggers(
                /* add your triggers */
        );
    }
}
