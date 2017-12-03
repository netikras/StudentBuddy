package com.netikras.studies.studentbuddy.core.data.sys;

public interface SysProp {

    /**
     * Session inactivity timeout. Default is 1hr
     */
    IntProperty SESSION_TIMEOUT = new IntProperty("session.timeout", 3600);

    /**
     * Sessions might be disabled, i.e. noone would be able to login except for user 'system'
     */
    BooleanProperty SESSION_ENABLE = new BooleanProperty("session.enabled", true);

    /**
     * All sessions are currently suspended to prevent any activity. Could be for security or any other measures
     */
    BooleanProperty SESSION_SUSPEND = new BooleanProperty("session.suspend", false);

    /**
     * If user John is logged in to the system and attempts to login as another user and fails John will not be <br/>
     * logged out if this value is set to 'true'
     */
    BooleanProperty FAILED_LOGIN_KEEP_USER = new BooleanProperty("session.login.failed.keepuser", true);

    /**
     * Maximum depth of returned DTO structure mapped from model object.
     */
    IntProperty DTO_RETURN_DEPTH = new IntProperty("dto.return.depth", 2);









    class IntProperty {
        private String name;
        private int defaultValue;

        IntProperty(String name, int defaultValue) {
            this.name = name;
            this.defaultValue = defaultValue;
        }

        public String getName() {
            return name;
        }

        public int getDefaultValue() {
            return defaultValue;
        }

    }

    class BooleanProperty {
        private String name;
        private boolean defaultValue;

        BooleanProperty(String name, boolean defaultValue) {
            this.name = name;
            this.defaultValue = defaultValue;
        }

        public String getName() {
            return name;
        }

        public boolean getDefaultValue() {
            return defaultValue;
        }

    }

    class StringProperty {
        private String name;
        private String defaultValue;

        StringProperty(String name, String defaultValue) {
            this.name = name;
            this.defaultValue = defaultValue;
        }

        public String getName() {
            return name;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

    }

    class LongProperty {
        private String name;
        private Long defaultValue;

        LongProperty(String name, Long defaultValue) {
            this.name = name;
            this.defaultValue = defaultValue;
        }

        public String getName() {
            return name;
        }

        public Long getDefaultValue() {
            return defaultValue;
        }
    }

}



