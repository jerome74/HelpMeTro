package it.mygeo.project.service.robot;

import it.mygeo.project.R;
import android.content.Intent;


public enum ServiceType 
{
    INCOMING(R.string.incoming_time),
    REGULAR(R.string.regular_time),
    UNKNOWN(R.string.unknown_time),
    IMMEDIATE(R.string.immadiate_time);

    public final int resId;

    ServiceType(int resId) {
        this.resId = resId;
    }

    public static final String EXTRA = "it.mygeo.project.service.robot.ServiceType";

    public static ServiceType fromIntent(Intent intent) {
        if (intent.hasExtra(EXTRA)) {
            final String name = intent.getStringExtra(EXTRA);
            for (ServiceType type : values()) {
                if (type.name().equals(name)) {
                    return type;
                }
            }
            return UNKNOWN;
        } else {
            return UNKNOWN;
        }
    }
}
