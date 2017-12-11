package com.netikras.studies.studentbuddy.api.misc;

import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;


/**
 * Most of the code shamelessly stolen from {@link java.util.concurrent.TimeUnit}. <br/>
 * The difference is: a concept of
 */
public enum TimeUnit {

    YEARS("y") {
        public long toNanos(long d)   { return x(d, C8/C0, MAX/(C8/C0)); }
        public long toMicros(long d)  { return x(d, C8/C1, MAX/(C8/C1)); }
        public long toMillis(long d)  { return x(d, C8/C2, MAX/(C8/C2)); }
        public long toSeconds(long d) { return x(d, C8/C3, MAX/(C8/C3)); }
        public long toMinutes(long d) { return x(d, C8/C4, MAX/(C8/C4)); }
        public long toHours(long d)   { return x(d, C8/C5, MAX/(C8/C5)); }
        public long toDays(long d)    { return x(d, C8/C6, MAX/(C8/C6)); }
        public long toMonths(long d)  { return x(d, C8/C7, MAX/(C8/C7)); }
        public long toYears(long d)   { return d; }
        public long convert(long d, TimeUnit u) { return u.toYears(d); }
        int excessNanos(long d, long m) { return 0; }
    },
    MONTHS("M") {
        public long toNanos(long d)   { return x(d, C7/C0, MAX/(C7/C0)); }
        public long toMicros(long d)  { return x(d, C7/C1, MAX/(C7/C1)); }
        public long toMillis(long d)  { return x(d, C7/C2, MAX/(C7/C2)); }
        public long toSeconds(long d) { return x(d, C7/C3, MAX/(C7/C3)); }
        public long toMinutes(long d) { return x(d, C7/C4, MAX/(C7/C4)); }
        public long toHours(long d)   { return x(d, C7/C5, MAX/(C7/C5)); }
        public long toDays(long d)    { return x(d, C7/C6, MAX/(C7/C6)); }
        public long toMonths(long d)  { return d; }
        public long toYears(long d)   { return d/(C8/C7); }
        public long convert(long d, TimeUnit u) { return u.toMonths(d); }
        int excessNanos(long d, long m) { return 0; }
    },
    DAYS("d") {
        public long toNanos(long d)   { return x(d, C6/C0, MAX/(C6/C0)); }
        public long toMicros(long d)  { return x(d, C6/C1, MAX/(C6/C1)); }
        public long toMillis(long d)  { return x(d, C6/C2, MAX/(C6/C2)); }
        public long toSeconds(long d) { return x(d, C6/C3, MAX/(C6/C3)); }
        public long toMinutes(long d) { return x(d, C6/C4, MAX/(C6/C4)); }
        public long toHours(long d)   { return x(d, C6/C5, MAX/(C6/C5)); }
        public long toDays(long d)    { return d; }
        public long toMonths(long d)  { return d/(C7/C6); }
        public long toYears(long d)   { return d/(C8/C6); }
        public long convert(long d, TimeUnit u) { return u.toDays(d); }
        int excessNanos(long d, long m) { return 0; }
    },
    HOURS("H") {
        public long toNanos(long d)   { return x(d, C5/C0, MAX/(C5/C0)); }
        public long toMicros(long d)  { return x(d, C5/C1, MAX/(C5/C1)); }
        public long toMillis(long d)  { return x(d, C5/C2, MAX/(C5/C2)); }
        public long toSeconds(long d) { return x(d, C5/C3, MAX/(C5/C3)); }
        public long toMinutes(long d) { return x(d, C5/C4, MAX/(C5/C4)); }
        public long toHours(long d)   { return d; }
        public long toDays(long d)    { return d/(C6/C5); }
        public long toMonths(long d)  { return d/(C7/C5); }
        public long toYears(long d)   { return d/(C8/C5); }
        public long convert(long d, TimeUnit u) { return u.toHours(d); }
        int excessNanos(long d, long m) { return 0; }
    },
    MINUTES("m") {
        public long toNanos(long d)   { return x(d, C4/C0, MAX/(C4/C0)); }
        public long toMicros(long d)  { return x(d, C4/C1, MAX/(C4/C1)); }
        public long toMillis(long d)  { return x(d, C4/C2, MAX/(C4/C2)); }
        public long toSeconds(long d) { return x(d, C4/C3, MAX/(C4/C3)); }
        public long toMinutes(long d) { return d; }
        public long toHours(long d)   { return d/(C5/C4); }
        public long toDays(long d)    { return d/(C6/C4); }
        public long toMonths(long d)  { return d/(C7/C4); }
        public long toYears(long d)   { return d/(C8/C4); }
        public long convert(long d, TimeUnit u) { return u.toMinutes(d); }
        int excessNanos(long d, long m) { return 0; }
    },
    SECONDS("s") {
        public long toNanos(long d)   { return x(d, C3/C0, MAX/(C3/C0)); }
        public long toMicros(long d)  { return x(d, C3/C1, MAX/(C3/C1)); }
        public long toMillis(long d)  { return x(d, C3/C2, MAX/(C3/C2)); }
        public long toSeconds(long d) { return d; }
        public long toMinutes(long d) { return d/(C4/C3); }
        public long toHours(long d)   { return d/(C5/C3); }
        public long toDays(long d)    { return d/(C6/C3); }
        public long toMonths(long d)  { return d/(C7/C3); }
        public long toYears(long d)   { return d/(C8/C3); }
        public long convert(long d, TimeUnit u) { return u.toSeconds(d); }
        int excessNanos(long d, long m) { return 0; }
    },
    MILLISECONDS("S") {
        public long toNanos(long d)   { return x(d, C2/C0, MAX/(C2/C0)); }
        public long toMicros(long d)  { return x(d, C2/C1, MAX/(C2/C1)); }
        public long toMillis(long d)  { return d; }
        public long toSeconds(long d) { return d/(C3/C2); }
        public long toMinutes(long d) { return d/(C4/C2); }
        public long toHours(long d)   { return d/(C5/C2); }
        public long toDays(long d)    { return d/(C6/C2); }
        public long toMonths(long d)  { return d/(C7/C2); }
        public long toYears(long d)   { return d/(C8/C2); }
        public long convert(long d, TimeUnit u) { return u.toMillis(d); }
        int excessNanos(long d, long m) { return 0; }
    },
    MICROSECONDS("u") {
        public long toNanos(long d)   { return x(d, C1/C0, MAX/(C1/C0)); }
        public long toMicros(long d)  { return d; }
        public long toMillis(long d)  { return d/(C2/C1); }
        public long toSeconds(long d) { return d/(C3/C1); }
        public long toMinutes(long d) { return d/(C4/C1); }
        public long toHours(long d)   { return d/(C5/C1); }
        public long toDays(long d)    { return d/(C6/C1); }
        public long toMonths(long d)  { return d/(C7/C1); }
        public long toYears(long d)   { return d/(C8/C1); }
        public long convert(long d, TimeUnit u) { return u.toMicros(d); }
        int excessNanos(long d, long m) { return (int)((d*C1) - (m*C2)); }
    },
    NANOSECONDS("n") {
        public long toNanos(long d)   { return d; }
        public long toMicros(long d)  { return d/(C1/C0); }
        public long toMillis(long d)  { return d/(C2/C0); }
        public long toSeconds(long d) { return d/(C3/C0); }
        public long toMinutes(long d) { return d/(C4/C0); }
        public long toHours(long d)   { return d/(C5/C0); }
        public long toDays(long d)    { return d/(C6/C0); }
        public long toMonths(long d)  { return d/(C7/C0); }
        public long toYears(long d)   { return d/(C8/C0); }
        public long convert(long d, TimeUnit u) { return u.toNanos(d); }
        int excessNanos(long d, long m) { return (int)(d - (m*C2)); }
    },

    ;

    static final long C0 = 1L;
    static final long C1 = C0 * 1000L;
    static final long C2 = C1 * 1000L;
    static final long C3 = C2 * 1000L;
    static final long C4 = C3 * 60L;
    static final long C5 = C4 * 60L;
    static final long C6 = C5 * 24L;
    static final long C7 = C6 * 30L;
    static final long C8 = C6 * 365L;

    static final long MAX = Long.MAX_VALUE;

    static long x(long d, long m, long over) {
        if (d >  over) return Long.MAX_VALUE;
        if (d < -over) return Long.MIN_VALUE;
        return d * m;
    }

    private final String text;

    TimeUnit(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public long convert(long sourceDuration, TimeUnit sourceUnit) {
        throw new AbstractMethodError();
    }
    public long toNanos(long duration) {
        throw new AbstractMethodError();
    }
    public long toMicros(long duration) {
        throw new AbstractMethodError();
    }
    public long toMillis(long duration) {
        throw new AbstractMethodError();
    }
    public long toSeconds(long duration) {
        throw new AbstractMethodError();
    }
    public long toMinutes(long duration) {
        throw new AbstractMethodError();
    }
    public long toHours(long duration) {
        throw new AbstractMethodError();
    }
    public long toDays(long duration) {
        throw new AbstractMethodError();
    }
    public long toMonths(long duration) {
        throw new AbstractMethodError();
    }
    public long toYears(long duration) {
        throw new AbstractMethodError();
    }

    abstract int excessNanos(long d, long m);


    public static TimeUnit fromText(String t) {
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
