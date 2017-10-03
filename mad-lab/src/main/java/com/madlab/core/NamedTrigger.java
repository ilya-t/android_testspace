package com.madlab.core;

/**
 * Version of trigger with caption.
 */
public abstract class NamedTrigger implements Trigger {
    private final String caption;

    protected NamedTrigger(String caption) {
        this.caption = caption;
    }

    protected final String getCaption() {
        return caption;
    }
}
