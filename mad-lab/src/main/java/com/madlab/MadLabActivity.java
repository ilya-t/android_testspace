package com.madlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.madlab.core.*;
import com.madlab.core.NamedTrigger;
import com.madlab.core.Trigger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class MadLabActivity extends AppCompatActivity {
    private LinearLayout layoutActions;
    private FrameLayout layoutCountainer;
    public TextView tvOutput;
    private ArrayList<Trigger> cmdList = new ArrayList<>();
    private Map<View, Trigger> cmdMap = new HashMap<>();

    private Experiment experiment;

    private View.OnClickListener cmdClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Trigger cmd = cmdMap.get(v);
            if (cmd != null) {
                cmd.pull();
            }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
            int index = keyCodeToNumber(keyCode) - 1;
            if (index >= 0 && index < cmdList.size()) {
                cmdList.get(index).pull();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private int keyCodeToNumber(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_0: return 0;
            case KeyEvent.KEYCODE_1: return 1;
            case KeyEvent.KEYCODE_2: return 2;
            case KeyEvent.KEYCODE_3: return 3;
            case KeyEvent.KEYCODE_4: return 4;
            case KeyEvent.KEYCODE_5: return 5;
            case KeyEvent.KEYCODE_6: return 6;
            case KeyEvent.KEYCODE_7: return 7;
            case KeyEvent.KEYCODE_8: return 8;
            case KeyEvent.KEYCODE_9: return 9;
        }
        return -1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mad_lab_activity);


        experiment = createExperiment();

        if (experiment != null) {
            setTitle("Experiment: " + BuildConfig.EXPERIMENT_NAME);
        }

        layoutActions = (LinearLayout)findViewById(R.id.layout_actions);
        layoutCountainer = (FrameLayout)findViewById(R.id.layout_container);
        tvOutput = (TextView)findViewById(R.id.tv_output);
        injectStatic();

        int experimentLayout = experiment.getExperimentLayout();
        if (experimentLayout > 0) {
            layoutCountainer.addView(
                    LayoutInflater.from(this).inflate(experimentLayout, null)
            );
        }

        experiment.onSetupExperiment(this);
    }

    private void injectStatic() {
        Static.c = getApplicationContext();
        Static.r = getResources();
        Static.a = this;
        Static.tvOutput = tvOutput;
        Static.experiment = experiment;
    }

    public void addTriggers(Trigger... commands) {
        addControls(commands);
        Collections.addAll(cmdList, commands);
    }


    private void addControls(Trigger... commands) {
        int index = cmdList.size()+1;
        for (Trigger cmd : commands) {
            Button button = new Button(this);
            if (cmd instanceof NamedTrigger) {
                button.setText(((NamedTrigger) cmd).getCaption());
            } else {
                button.setText(String.valueOf(index));
            }

            button.setOnClickListener(cmdClick);
            button.setSingleLine();
            cmdMap.put(button, cmd);
            layoutActions.addView(button);
            index++;
        }
    }

    protected abstract Experiment createExperiment();

    public Experiment getExperiment() {
        return experiment;
    }
}

