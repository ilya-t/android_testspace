# Tiny App for quick Android-based experiments

This tiny app that builds fast and can be used for any kind of __reusable__ prototype or draft.

Start experiment by

```bash
./start_experiment.sh my_experiment
```
it will make a checkout to branch with given name that is based on master. Unsaved changes from previous experiment will be automatically commited and now you may do whatever you want!

### But wait! Here are couple of fancy tricks!
Take a look at `com.testspace.CurrentExperiment`, for example:

```kotlin
class CurrentExperiment(a: ExperimentActivity) : Experiment(a) {
    init {
        a.addTriggers(
                { a.tvOutput.setText("Trigger#1 pressed") },
                NamedTrigger("T2") { Static.output("Trigger#2 pressed!") }
        );
    }

    @LayoutRes
    override fun getExperimentLayout() = R.layout.basic_layout
}
```

For each trigger a button will be created, this button may be clicked or triggered by keyboard button 1 - to pull first trigger, 2 - to pull second.
This could be very useful at emulators.
