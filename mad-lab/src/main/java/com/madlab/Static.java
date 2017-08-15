package com.madlab;

import android.content.Context;
import android.content.res.Resources;
import android.widget.TextView;
import com.madlab.MadLabActivity;
import com.madlab.core.Experiment;

/**
 * Here you may access app's basic components despite leaks and code smells.
 * At experiments anything is permitted!
 *
 * Note that those fields injected only by {@link MadLabActivity}
 */
@SuppressWarnings("ALL")
public class Static {
    public static MadLabActivity a;
    /**
     * Application context.
     */
    public static Context c;
    public static Resources r;
    static TextView tvOutput;
    public static Experiment experiment;

    public static void output(String text) {
        tvOutput.setText(text);
    }
}
