package com.madlab.core;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.madlab.MadLabActivity;

/**
 * Experiment is a kind of control panel with triggers
 * that you can pull to see what happens at your test zone
 * - place where you may observe your experiment.
 * To configure your triggers at control panel use {@link MadLabActivity#addTriggers(Trigger...)}.
 * To setup your test zone use {@link #getExperimentLayout()}.
 */
public class Experiment {
    private final MadLabActivity activity;

    public Experiment(@NonNull MadLabActivity a) {
        activity = a;
    }

    public MadLabActivity getActivity() {
        return activity;
    }

    /**
     * Override this method to provide own experiment layout.
     */
    @LayoutRes
    public int getExperimentLayout() {
        return 0;
    }
}
