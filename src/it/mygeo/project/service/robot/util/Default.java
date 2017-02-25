package it.mygeo.project.service.robot.util;

import it.mygeo.project.service.robot.preferences.Preferences;

public class Default {
    /**
     * Default value for {@link Preferences#ENABLE_AUTO_SYNC}.
     */
    public static final boolean ENABLE_AUTO_SYNC = false;
    /**
     * Default value for {@link Preferences#INCOMING_TIMEOUT_SECONDS}.
     */
    public static final int INCOMING_TIMEOUT_SECONDS = 60 * 3;
    /**
     * Default value for {@link Preferences#REGULAR_TIMEOUT_SECONDS}.
     */
    public static final int REGULAR_TIMEOUT_SECONDS = 2 * 60 * 60; // 2h
}
