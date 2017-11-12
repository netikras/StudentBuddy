package com.netikras.studies.studentbuddy.api.misc;

import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

public enum TimeUnit {

    MONTHS("M"),
    DAYS("d"),
    HOURS("H"),
    MINUTES("m"),
    SECONDS("s")
    ;

    private final String text;

    TimeUnit(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public TimeUnit fromText(String t) {
        if (!isNullOrEmpty(t)) {
            for (TimeUnit timeUnit : values()) {
                if (timeUnit.getText().equals(t)) {
                    return timeUnit;
                }
            }
        }
        return null;
    }
}
