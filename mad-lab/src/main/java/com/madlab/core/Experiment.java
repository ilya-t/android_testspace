package com.madlab.core;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.madlab.MadLabActivity;

/**
 * Experiment is a kind of control panel with triggers
 * that you can pull to see what happens at your test zone
 * - place where you may observe your experiment.
 * To configure your triggers at control panel use {@link MadLabActivity#addTriggers(Trigger...)}.
 * To setup your test zone use {@link #getExperimentLayout()}.
 */
public interface Experiment {
    @LayoutRes int getExperimentLayout();

    /**
     * Called at {@link android.app.Activity#onCreate(Bundle)}
     */
    void onSetupExperiment(@NonNull MadLabActivity a);
}
