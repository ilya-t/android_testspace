package com.testspace;

import com.madlab.MadLabActivity;
import com.madlab.core.Experiment;

public class MainActivity extends MadLabActivity {
    @Override
    protected Experiment createExperiment() {
        return new CurrentExperiment();
    }
}
