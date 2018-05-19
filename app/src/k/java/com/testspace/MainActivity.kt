package com.testspace

import com.madlab.MadLabActivity

class MainActivity : MadLabActivity() {
    override fun createExperiment() = CurrentExperiment()
}
